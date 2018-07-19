<template>
	<el-row class="container">
		<el-col :span="24" class="header">
			<el-col :span="10" class="logo">
				<img src="../assets/images/backend/logo.png"/>
			</el-col>
			<el-col :span="4" class="userinfo">
				<top-user :enable-home-menu=true :enable-manage-menu=false></top-user>
			</el-col>
		</el-col>
		<el-col :span="24" class="main">
			<aside class="menu-expanded">
				<!--导航菜单-->
				<el-menu 
				unique-opened 
				router 
				class="el-menu-vertical-demo" 
				:default-active="$route.path" 				
				:default-openeds="openeds"
				@open="handleopen" 
				@close="handleclose" 
				@select="handleselect">
					<template v-for="(item,index) in this.routes" v-if="!item.hidden">
						<el-submenu :key="index" :index="index+''">
							<template slot="title">
								<i :class="item.icon"></i>
								<span>{{item.name}}</span>
							</template>
							<el-menu-item v-for="child in item.children" :index="child.path" :key="child.path" v-if="!child.hidden">
									<i :class="child.icon"></i>
								<span>{{child.name}}</span>
							</el-menu-item>
						</el-submenu>
					</template>
				</el-menu>
			</aside>
			<section class="content-container">
				<div class="grid-content bg-purple-light">
					<el-col :span="24" class="breadcrumb-container">
						<el-breadcrumb separator-class="el-icon-arrow-right">						
							<el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
						</el-breadcrumb>						
					</el-col>
					<el-col :span="24" class="content-wrapper">
						<transition name="fade" mode="out-in">
							<router-view></router-view>
						</transition>
					</el-col>
				</div>
			</section>
		</el-col>
	</el-row>
</template>

<script>
	import topUser from './common/TopUser.vue'

	export default {
		components:{
			topUser
		},
		data() {
			return {
				openeds: ['0']
			}
		},
		methods: {
			onSubmit() {
				console.log('submit!');
			},
			handleopen() {
				//console.log('handleopen');
			},
			handleclose() {
				//console.log('handleclose');
			},
			handleselect: function (a, b) {
			},			
			showMenu(i,status){
				this.$refs.menuCollapsed.getElementsByClassName('submenu-hook-'+i)[0].style.display=status?'block':'none'
			}
		},
        computed: {
            routes(){
                return this.$store.state.routes
            }
		}
	}

</script>

<style lang="scss">
	@import '../assets/styles/backend-common.scss';
</style>
<style lang="scss" scoped>
	@import '../assets/styles/vars.scss';
	@import './Manage.scss';
</style>
