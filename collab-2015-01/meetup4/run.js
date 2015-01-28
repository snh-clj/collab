try {
    require("source-map-support").install();
} catch(err) {
}
require("./out/goog/bootstrap/nodejs.js");
require("./out/rottentomatoes_query.js");
goog.require("rottentomatoes_query.core");
goog.require("cljs.nodejscli");
