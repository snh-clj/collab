# rottentomatoes-query

## Usage

1. Register at http://developer.rottentomatoes.com/ for an API key.
1. ```export RT_API_KEY=<your key>```
1. Clone this repo.
1. In one terminal:
 1. ```cd``` into this repo
 1. ```lein cljsbuild auto```
1. In a second terminal:
 1. ```node ./run.js``` (note that your node executable might be named
    ```nodejs``` instead)
