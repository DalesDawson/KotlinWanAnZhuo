package com.daledawson.wananzhuo_kotlin

/**
 * 创 建 人：zhengquan
 * 创建日期：2019/10/17
 * 修改时间：
 * 修改备注：
 */
class Api {
    companion object {
        const val HOST = "https://www.wanandroid.com/"

        // 轮播图
        const val BANNER_LIST = "banner/json"

        //首页数据
        const val HOME_LIST = "article/list/"

        //热门搜索==>http://www.wanandroid.com/hotkey/json
        const val HOT_WORD = "hotkey/json"

        const val HOT_FRIEND = "friend/json"

        //搜索
        const val SEARCH_WORD = "article/query/"

        //知识体系http://www.wanandroid.com/tree/json
        const val KNOWLEDGE_TREE = "tree/json"

        //具体标签下的文章http://www.wanandroid.com/article/list/0/json?cid=168
        const val KNOWLEDGE_LIST = "article/list/"

        //项目http://www.wanandroid.com/project/tree/json
        const val PROJECT_TREE = "project/tree/json"

        //项目列表http://www.wanandroid.com/project/list/1/json?cid=294
        const val PROJECT_LIST = "project/list/"

        //注册 https://www.wanandroid.com/user/register
        const val REGISTER = "user/register"

        //登录 https://www.wanandroid.com/user/login
        const val LOGIN = "user/login"

        //退出登录 https://www.wanandroid.com/user/logout/json
        /**
         * 访问了 logout 后，服务端会让客户端清除 Cookie（即cookie max-Age=0），
         * 如果客户端 Cookie 实现合理，可以实现自动清理，如果本地做了用户账号密码和保存，及时清理。
         */
        const val LOGOUT = "user/logout/json"
    }
}