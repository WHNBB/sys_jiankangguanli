<template>
    <div>
        <div>新闻内容</div>
        {{content}}
    </div>
</template>
<script>
export default {
    data(){
        return{
            content:"",
        };
    },
    methods:{
        gun:function(){
            this.$http.post(
                "http://api.komavideo.com/news/list"
            ).
            then(body =>{
                this.content = body.data;
            });
        }
    },
    mounted:{
        
    }
}
</script>
<style lang="less" scoped>

</style>