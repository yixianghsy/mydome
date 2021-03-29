<template>
  <div>
    	<!-- header -->
	<div class="header">
		<a href="http://www.e3mall.cn"><img src="../images/logo.png" border="0"><span>欢迎登录</span></a>
	</div>
	<!-- login_main -->
	<div class="login_main clear">
		<div class="pic">
			<a href="http://www.e3mall.cn/html/activity/1472720729.html" target="_blank"><img src="../images/06f42c372620f92b40da77a8b23cdf7f.png"></a>
		</div>
		<!-- login -->
		<div class="login">
			<div class="login_header">
    	<span>您还未登录</span>
    	<a href="/page/register">免费注册</a>
    </div>

			<div class="login_box clear">
				<ul class="loginnav">
					<li class="curr" mark="sfbest"><em></em>优选账号</li>
				</ul>

				<div class="logincon">
					<ul>
            <!--form  start -->
                <el-form  :model="loginForm" class="login-form" autocomplete="on" label-position="left">
                  <div class="title-container">
                    <h3 class="title">分布式电商后台管理系统</h3>
                  </div>
                  <el-form-item prop="username">
                    <span class="svg-container">
                      <svg-icon icon-class="user" />
                    </span>
                    <el-input
                      v-model="loginForm.username"
                      placeholder="用户名"
                      name="username"
                      type="text"
                      tabindex="1"
                      autocomplete="on"
                    />
                  </el-form-item>

                  <el-tooltip v-model="capsTooltip" content="Caps lock is On" placement="right" manual>
                    <el-form-item prop="password">
                      <span class="svg-container">
                        <svg-icon icon-class="password" />
                      </span>
                      <el-input
                        :key="passwordType"
                        v-model="loginForm.password"
                        :type="passwordType"
                        placeholder="密码"
                        name="password"
                        tabindex="2"
                        autocomplete="on"
                        @keyup.native="checkCapslock"
                        @blur="capsTooltip = false"
                        @keyup.enter.native="handleLogin"
                      />
                      <!-- <span class="show-pwd" @click="showPwd">
                        <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
                      </span> -->
                    </el-form-item>
                  </el-tooltip>

                  <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="submitHandler">登录</el-button>

                </el-form>
            <!--form  and   -->
					</ul>
				</div>


				<ul class="blink">
					<li class="p-f10">
						<h2 class="h2">合作网站账户登录：</h2>
						<div>
							<a href="http://api2.e3mall.cn/unionlogin/qq/oauth/qq_login.php?returnUrl=http://www.e3mall.cn/" class="link">QQ</a>&nbsp;|&nbsp; <a href="https://api.weibo.com/oauth2/authorize?client_id=1800908332&amp;redirect_uri=https%3A%2F%2Fpassport.e3mall.cn%2Fcallback%2Fsina&amp;response_type=code&amp;state=&amp;display=?returnUrl=http://www.e3mall.cn/" class="link">新浪微博</a> &nbsp;|&nbsp; <a href="https://open.t.qq.com/cgi-bin/oauth2/authorize?client_id=801198099&amp;redirect_uri=https%3A%2F%2Fpassport.e3mall.cn%2Fcallback%2Fqq&amp;response_type=code&amp;type=?returnUrl=http://www.e3mall.cn/" class="link">腾讯微博</a> &nbsp;|&nbsp; <a href="http://api2.e3mall.cn/unionlogin/alipay.php?returnUrl=http://www.e3mall.cn/" class="link">支付宝</a>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<!-- /login -->
	</div>
	<!-- /login_main -->
	<div class="footer">
		<span> <a href="http://www.e3mall.cn/www/379/5109.html" rel="nofollow" class="footerlink1">关于我们</a> | <a href="http://www.e3mall.cn/www/380/5116.html" rel="nofollow" class="footerlink1">联系我们</a> | <a href="http://www.e3mall.cn/www/381/5117.html" rel="nofollow" class="footerlink1">招聘人才</a> | <a href="http://www.e3mall.cn/www/330/2705.html" rel="nofollow" class="footerlink1">友情链接</a> | <a href="http://supplier.e3mall.cn/supplierApply" rel="nofollow" class="footerlink1">供应商申请</a>
		</span>
		<p>
			北京宜立方电子商务有限公司<br> 北京市公安局顺义分局备案11011302000890号|<a href="http://www.miibeian.gov.cn" target="_blank" rel="nofollow" class="footerlink1">京ICP备12011349号</a>|<a href="http://www.e3mall.cn/www/174/461.html" target="_blank" rel="nofollow" class="footerlink1">企业营业执照</a><br> Copyright© 宜立方商城
			e3mall.cn 版权所有<br>
		</p>
	</div>
  </div>
</template>
<script>
import { validUsername } from '@/utils/validate'

export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error('请输入正确的用户名'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('密码不能小于6位'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: '',
        password: ''
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      passwordType: 'password',
      capsTooltip: false,
      loading: false,
      showDialog: false,
      redirect: undefined,
      otherQuery: {}
    }
  },
  // watch: {
  //   $route: {
  //     handler: function(route) {
  //       const query = route.query
  //       if (query) {
  //         this.redirect = query.redirect
  //         this.otherQuery = this.getOtherQuery(query)
  //       }
  //     },
  //     immediate: true
  //   }
  // },
  created() {
    // window.addEventListener('storage', this.afterQRScan)
  },
  destroyed() {
    // window.removeEventListener('storage', this.afterQRScan)
  },
  methods: {
          submitHandler(e){
              e.preventDefault()
              // this.$http(
              //     {
              //          url: "/test",
              //          method: "get",
              //          crossdomain: true,
              //         params: {
              //        username:this.model.username,
              //        password:this.model.password
              //         }
              //          }).then(res => {
              //          console.log(res.data);
              //         });
              //      }
              //     }
              // )


              this.$http.get('/user/register',{params:this.model}).then(res=>{
                  console.log(res.data.success)
              }).catch(err=>{
                  console.log(err)
              })
          },

    checkCapslock(e) {
      const { key } = e
      this.capsTooltip = key && key.length === 1 && (key >= 'A' && key <= 'Z')
    },

    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          // 调用store中user模块的actions
          //this.$store.dispatch(‘action方法名’,值)
          this.$store.dispatch('user/login', this.loginForm)
            .then(() => {
              this.$router.push({ path: this.redirect || '/', query: this.otherQuery })
              this.loading = false
            })
            .catch(() => {
              this.loading = false
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    getOtherQuery(query) {
      return Object.keys(query).reduce((acc, cur) => {
        if (cur !== 'redirect') {
          acc[cur] = query[cur]
        }
        return acc
      }, {})
    }
  }
}
</script>
<style >
@import '../styles/headerfooterindex.css';
@import '../styles/jquery.alerts.css';
@import '../styles/login.css';
</style>