# Vue3核心

1. vue-cli项目创建

   可以从vue ui中创建，包管理器选npm，预设选default即可

2. 安装插件和依赖

   如 vue-router插件 vuex插件；bootstrap依赖 jQuery依赖

   router-link跳转性能更好，相当于一次性加载所有资源

3. 一个vue文件的组成

   * template部分 html代码
   * script部分 js代码
   * style部分 css代码 scoped可以使不同组件的css样式互不影响

4. 引入组件

   ```js
   <script>
   import HelloWorld form '@/components/HelloWorld.vue'
   
   export default {
       name: 'HomeView',
       props: {
           user: {
               type: Object,
               required: true,
           },
       }
       components: {
           HelloWorld
       }
   }
   </script>
   ```

5. 基本概念

   * setup(props, context)初始化变量、函数

     * ref定义变量，可以用.value属性重新赋值
     * reactive定义对象/变量，不可重新赋值
     * props存储父组件传递过来的数据
     * context.emit() 触发父组件绑定的函数

   * computed 动态计算某个数据

   * 在父子组件之间传递信息

     `v-bind:user="user"` 单向绑定

     `v-model` 双向绑定

     `v-on:click`

     `v-if`

     `v-for`

     

