#!/usr/bin/env bash
# usage: ./set-up-collab.sh <YYYY-MM[-DD]> <repo-url>

set -ex

COLLAB_DIR=collab-${1}
REPO_URL=$2

mkdir -p ${COLLAB_DIR}
git clone ${REPO_URL} ${COLLAB_DIR}/meetup0

for group in {1..9}; do
    rsync -av ${COLLAB_DIR}/meetup0/ ${COLLAB_DIR}/meetup${group}/
done

rm current-collab && ln -s ${COLLAB_DIR} current-collab
