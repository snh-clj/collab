(ns meet-async.dev)


(defmacro websocket-url-host []
  (if-let [host (System/getenv (str "LEIN_FIGWHEEL_HOST"))]
    host
    (throw (Exception. (str "Missing LEIN_FIGWHEEL_HOST environment variable. Set it.")))))

(defmacro websocket-url-port []
  (let [port (System/getenv (str "LEIN_FIGWHEEL_PORT"))]
    (if (< 0 (count port))
      (Long/parseLong port)
      (throw (Exception. (str "Missing LEIN_FIGWHEEL_PORT environment variable. Set it."))))))

(defmacro websocket-url []
  (let [host (websocket-url-host)
        port (websocket-url-port)]
    (str "ws://" host ":" port  "/figwheel-ws")))
