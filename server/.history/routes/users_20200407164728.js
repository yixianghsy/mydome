var express = require('express');
var router = express.Router();
const querySql = require('../db/index')
const { PWD_SALT, PRIVATE_KEY, EXPIRESD } = require('../utils/constant')
const { md5, upload } = require('../utils/index')
const jwt = require('jsonwebtoken')


/* 注册接口 */
router.post('/register', async (req, res, next) => {
  let { username, password, nickname } = req.body
  try {
    let user = await querySql('select * from user where username = ?', [username])
    if (!user || user.length === 0) {
      password = md5(`${password}${PWD_SALT}`)
      await querySql('insert into user(username,password,nickname) value(?,?,?)', [username, password, nickname])
      res.send({ code: 0, msg: '注册成功' })
    } else {
      res.send({ code: -1, msg: '该账号已注册' })
    }
  } catch (e) {
    console.log(e)
    next(e)
  }
});
//登录接口
router.post('/login', async (req, res, next) => {
      
  ｝）