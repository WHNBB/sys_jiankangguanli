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
    mounted:{
        this.$http.post("http://api.komavideo.com/news/list").then(body =>{
            this.content = body.data;
        });
    }
}
</script>
<style lang="less" scoped>

</style>