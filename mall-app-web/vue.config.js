module.exports = {
  baseUrl: './',
  configureWebpack:{
      devServer:{
        proxy: 'http://localhost:8081'
      }
  },
  css: {
    loaderOptions: {
      stylus: {
        'resolve url': true,
        'import': [
          './src/theme'
        ]
      }
    }
  },
  pluginOptions: {
    'cube-ui': {
      postCompile: true,
      theme: true
    }
  }
}
