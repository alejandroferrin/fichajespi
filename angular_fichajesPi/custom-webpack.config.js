const webpack = require('webpack');

module.exports = {
  plugins: [
    new webpack.DefinePlugin({
      $ENV: {
        apiURL: JSON.stringify(process.env.apiURL)
      }
    })
  ]
};