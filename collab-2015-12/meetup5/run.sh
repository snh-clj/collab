#!/usr/bin/env bash
# Run one of four preconfigured peers.
# Usage: ./run.sh { 0 | 1 | 2 | 3 }

PEER="$1" && shift
BASE_PORT=${BASE_PORT:-2400}

if [ ! "$PEER" ]; then
    echo error: missing PEER
    exit 1
fi

if [[ ! "$PEER" =~ ^[0123]$ ]]; then
    echo error: PEER must be 0-3
    exit 1
fi

lein run -- \
    --input-file data0${PEER}.edn \
    --uuid uuid:${PEER} \
    --host-port 127.0.0.1:$(( $BASE_PORT + ${PEER} )) \
    --peer 127.0.0.1:$(( $BASE_PORT + (($PEER + 1) % 4) )) \
    "$@"
