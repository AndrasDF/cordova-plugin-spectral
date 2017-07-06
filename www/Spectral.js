var exec = require('cordova/exec');

var PLUGIN_NAME = 'Spectral';

var Spectral = {
  test: function(test) {
    alert("test");
  },
  echo: function(phrase, cb) {
    exec(cb, null, PLUGIN_NAME, 'echo', [phrase]);
  },
  scanPhoto: function(url, success, error) {
	exec(success, error, PLUGIN_NAME, 'scanPhoto', [url]);
  },
  getDate: function(cb) {
    exec(cb, null, PLUGIN_NAME, 'getDate', []);
  }
};

module.exports = Spectral;