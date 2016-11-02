var fs = require('fs');
var csv = require('fast-csv');

function writeToFile() {
  var stream = csv.createWriteStream({headers: false}),
    writableStream = fs.createWriteStream('rates.csv');

  var currencies = ['AUD', 'BGN', 'BRL', 'CAD', 'CHF', 'CNY', 'CZK', 'DKK', 'GBP', 'HKD', 'HRK', 'HUF', 'IDR', 'ILS', 'INR', 'JPY', 'KRW', 'MXN', 'MYR', 'NOK', 'NZD', 'PHP', 'PLN', 'RON', 'RUB', 'SEK', 'SGD', 'THB', 'TRY', 'USD', 'ZAR'];

  writableStream.on('finish', function () {
    console.log('done');
  });

  stream.pipe(writableStream);
  for (var i = 0; i < 10; i++) {
    var obj = [Math.random().toString(36).substring(5), ((Math.random() * 20.0) + 0.0).toFixed(3), currencies[Math.floor(Math.random() * (currencies.length - 1))]];
    stream.write(obj);
  }
  stream.end();
}

writeToFile();