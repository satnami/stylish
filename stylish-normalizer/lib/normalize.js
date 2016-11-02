var config = require('../config/index');
var fs = require('fs');
var zlib = require('zlib');
var split = require('split');
var request = require('request');
var transform = require('stream-transform');
var baseCurrency = '';

var transformer = function (data, callback) {
  data = data.split(',');
  request(config.server + '/' + data[2] + '/' + baseCurrency + '/' + data[1], function (error, response, body) {
    if (!error && response.statusCode == 200) {
      callback(null, (data[0] + ',' + body + ',' + baseCurrency + '\n'))
    } else {
      console.log('error');
    }
  })
};

function transform_stream(file_path, currency) {
  baseCurrency = currency;
  var stream = fs.createReadStream(file_path)
    .pipe(split())
    .pipe(transform(transformer))
    .pipe(fs.createWriteStream('out.csv'));
}

module.exports = {
  transform: transform_stream
};