package com.example.githubapisample.data.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;
import java.util.Objects;

public class Commit {

    /**
     * url : https://api.github.com/repos/octocat/Hello-World/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e
     * sha : 6dcb09b5b57875f334f61aebed695e2e4193db5e
     * node_id : MDY6Q29tbWl0NmRjYjA5YjViNTc4NzVmMzM0ZjYxYWViZWQ2OTVlMmU0MTkzZGI1ZQ==
     * html_url : https://github.com/octocat/Hello-World/commit/6dcb09b5b57875f334f61aebed695e2e4193db5e
     * comments_url : https://api.github.com/repos/octocat/Hello-World/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e/comments
     * commit : {"url":"https://api.github.com/repos/octocat/Hello-World/git/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e","author":{"name":"Monalisa Octocat","email":"support@github.com","date":"2011-04-14T16:00:49Z"},"committer":{"name":"Monalisa Octocat","email":"support@github.com","date":"2011-04-14T16:00:49Z"},"message":"Fix all the bugs","tree":{"url":"https://api.github.com/repos/octocat/Hello-World/tree/6dcb09b5b57875f334f61aebed695e2e4193db5e","sha":"6dcb09b5b57875f334f61aebed695e2e4193db5e"},"comment_count":0,"verification":{"verified":false,"reason":"unsigned","signature":null,"payload":null}}
     * author : {"login":"octocat","id":1,"node_id":"MDQ6VXNlcjE=","avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false}
     * committer : {"login":"octocat","id":1,"node_id":"MDQ6VXNlcjE=","avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false}
     * parents : [{"url":"https://api.github.com/repos/octocat/Hello-World/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e","sha":"6dcb09b5b57875f334f61aebed695e2e4193db5e"}]
     */

    private String url;
    private String sha;
    private String node_id;
    private String html_url;
    private String comments_url;
    private CommitBean commit;
    private AuthorBeanX author;
    private CommitterBeanX committer;
    private List<ParentsBean> parents;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public CommitBean getCommit() {
        return commit;
    }

    public void setCommit(CommitBean commit) {
        this.commit = commit;
    }

    public AuthorBeanX getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBeanX author) {
        this.author = author;
    }

    public CommitterBeanX getCommitter() {
        return committer;
    }

    public void setCommitter(CommitterBeanX committer) {
        this.committer = committer;
    }

    public List<ParentsBean> getParents() {
        return parents;
    }

    public void setParents(List<ParentsBean> parents) {
        this.parents = parents;
    }

    public static class CommitBean {
        /**
         * url : https://api.github.com/repos/octocat/Hello-World/git/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e
         * author : {"name":"Monalisa Octocat","email":"support@github.com","date":"2011-04-14T16:00:49Z"}
         * committer : {"name":"Monalisa Octocat","email":"support@github.com","date":"2011-04-14T16:00:49Z"}
         * message : Fix all the bugs
         * tree : {"url":"https://api.github.com/repos/octocat/Hello-World/tree/6dcb09b5b57875f334f61aebed695e2e4193db5e","sha":"6dcb09b5b57875f334f61aebed695e2e4193db5e"}
         * comment_count : 0
         * verification : {"verified":false,"reason":"unsigned","signature":null,"payload":null}
         */

        private String url;
        private AuthorBean author;
        private CommitterBean committer;
        private String message;
        private TreeBean tree;
        private int comment_count;
        private VerificationBean verification;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public CommitterBean getCommitter() {
            return committer;
        }

        public void setCommitter(CommitterBean committer) {
            this.committer = committer;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public TreeBean getTree() {
            return tree;
        }

        public void setTree(TreeBean tree) {
            this.tree = tree;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public VerificationBean getVerification() {
            return verification;
        }

        public void setVerification(VerificationBean verification) {
            this.verification = verification;
        }

        public static class AuthorBean {
            /**
             * name : Monalisa Octocat
             * email : support@github.com
             * date : 2011-04-14T16:00:49Z
             */

            private String name;
            private String email;
            private String date;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }

        public static class CommitterBean {
            /**
             * name : Monalisa Octocat
             * email : support@github.com
             * date : 2011-04-14T16:00:49Z
             */

            private String name;
            private String email;
            private String date;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }

        public static class TreeBean {
            /**
             * url : https://api.github.com/repos/octocat/Hello-World/tree/6dcb09b5b57875f334f61aebed695e2e4193db5e
             * sha : 6dcb09b5b57875f334f61aebed695e2e4193db5e
             */

            private String url;
            private String sha;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getSha() {
                return sha;
            }

            public void setSha(String sha) {
                this.sha = sha;
            }
        }

        public static class VerificationBean {
            /**
             * verified : false
             * reason : unsigned
             * signature : null
             * payload : null
             */

            private boolean verified;
            private String reason;
            private Object signature;
            private Object payload;

            public boolean isVerified() {
                return verified;
            }

            public void setVerified(boolean verified) {
                this.verified = verified;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public Object getSignature() {
                return signature;
            }

            public void setSignature(Object signature) {
                this.signature = signature;
            }

            public Object getPayload() {
                return payload;
            }

            public void setPayload(Object payload) {
                this.payload = payload;
            }
        }
    }

    public static class AuthorBeanX {
        /**
         * login : octocat
         * id : 1
         * node_id : MDQ6VXNlcjE=
         * avatar_url : https://github.com/images/error/octocat_happy.gif
         * gravatar_id :
         * url : https://api.github.com/users/octocat
         * html_url : https://github.com/octocat
         * followers_url : https://api.github.com/users/octocat/followers
         * following_url : https://api.github.com/users/octocat/following{/other_user}
         * gists_url : https://api.github.com/users/octocat/gists{/gist_id}
         * starred_url : https://api.github.com/users/octocat/starred{/owner}{/repo}
         * subscriptions_url : https://api.github.com/users/octocat/subscriptions
         * organizations_url : https://api.github.com/users/octocat/orgs
         * repos_url : https://api.github.com/users/octocat/repos
         * events_url : https://api.github.com/users/octocat/events{/privacy}
         * received_events_url : https://api.github.com/users/octocat/received_events
         * type : User
         * site_admin : false
         */

        private String login;
        private int id;
        private String node_id;
        private String avatar_url;
        private String gravatar_id;
        private String url;
        private String html_url;
        private String followers_url;
        private String following_url;
        private String gists_url;
        private String starred_url;
        private String subscriptions_url;
        private String organizations_url;
        private String repos_url;
        private String events_url;
        private String received_events_url;
        private String type;
        private boolean site_admin;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNode_id() {
            return node_id;
        }

        public void setNode_id(String node_id) {
            this.node_id = node_id;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getGravatar_id() {
            return gravatar_id;
        }

        public void setGravatar_id(String gravatar_id) {
            this.gravatar_id = gravatar_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHtml_url() {
            return html_url;
        }

        public void setHtml_url(String html_url) {
            this.html_url = html_url;
        }

        public String getFollowers_url() {
            return followers_url;
        }

        public void setFollowers_url(String followers_url) {
            this.followers_url = followers_url;
        }

        public String getFollowing_url() {
            return following_url;
        }

        public void setFollowing_url(String following_url) {
            this.following_url = following_url;
        }

        public String getGists_url() {
            return gists_url;
        }

        public void setGists_url(String gists_url) {
            this.gists_url = gists_url;
        }

        public String getStarred_url() {
            return starred_url;
        }

        public void setStarred_url(String starred_url) {
            this.starred_url = starred_url;
        }

        public String getSubscriptions_url() {
            return subscriptions_url;
        }

        public void setSubscriptions_url(String subscriptions_url) {
            this.subscriptions_url = subscriptions_url;
        }

        public String getOrganizations_url() {
            return organizations_url;
        }

        public void setOrganizations_url(String organizations_url) {
            this.organizations_url = organizations_url;
        }

        public String getRepos_url() {
            return repos_url;
        }

        public void setRepos_url(String repos_url) {
            this.repos_url = repos_url;
        }

        public String getEvents_url() {
            return events_url;
        }

        public void setEvents_url(String events_url) {
            this.events_url = events_url;
        }

        public String getReceived_events_url() {
            return received_events_url;
        }

        public void setReceived_events_url(String received_events_url) {
            this.received_events_url = received_events_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isSite_admin() {
            return site_admin;
        }

        public void setSite_admin(boolean site_admin) {
            this.site_admin = site_admin;
        }
    }

    public static class CommitterBeanX {
        /**
         * login : octocat
         * id : 1
         * node_id : MDQ6VXNlcjE=
         * avatar_url : https://github.com/images/error/octocat_happy.gif
         * gravatar_id :
         * url : https://api.github.com/users/octocat
         * html_url : https://github.com/octocat
         * followers_url : https://api.github.com/users/octocat/followers
         * following_url : https://api.github.com/users/octocat/following{/other_user}
         * gists_url : https://api.github.com/users/octocat/gists{/gist_id}
         * starred_url : https://api.github.com/users/octocat/starred{/owner}{/repo}
         * subscriptions_url : https://api.github.com/users/octocat/subscriptions
         * organizations_url : https://api.github.com/users/octocat/orgs
         * repos_url : https://api.github.com/users/octocat/repos
         * events_url : https://api.github.com/users/octocat/events{/privacy}
         * received_events_url : https://api.github.com/users/octocat/received_events
         * type : User
         * site_admin : false
         */

        private String login;
        private int id;
        private String node_id;
        private String avatar_url;
        private String gravatar_id;
        private String url;
        private String html_url;
        private String followers_url;
        private String following_url;
        private String gists_url;
        private String starred_url;
        private String subscriptions_url;
        private String organizations_url;
        private String repos_url;
        private String events_url;
        private String received_events_url;
        private String type;
        private boolean site_admin;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNode_id() {
            return node_id;
        }

        public void setNode_id(String node_id) {
            this.node_id = node_id;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getGravatar_id() {
            return gravatar_id;
        }

        public void setGravatar_id(String gravatar_id) {
            this.gravatar_id = gravatar_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHtml_url() {
            return html_url;
        }

        public void setHtml_url(String html_url) {
            this.html_url = html_url;
        }

        public String getFollowers_url() {
            return followers_url;
        }

        public void setFollowers_url(String followers_url) {
            this.followers_url = followers_url;
        }

        public String getFollowing_url() {
            return following_url;
        }

        public void setFollowing_url(String following_url) {
            this.following_url = following_url;
        }

        public String getGists_url() {
            return gists_url;
        }

        public void setGists_url(String gists_url) {
            this.gists_url = gists_url;
        }

        public String getStarred_url() {
            return starred_url;
        }

        public void setStarred_url(String starred_url) {
            this.starred_url = starred_url;
        }

        public String getSubscriptions_url() {
            return subscriptions_url;
        }

        public void setSubscriptions_url(String subscriptions_url) {
            this.subscriptions_url = subscriptions_url;
        }

        public String getOrganizations_url() {
            return organizations_url;
        }

        public void setOrganizations_url(String organizations_url) {
            this.organizations_url = organizations_url;
        }

        public String getRepos_url() {
            return repos_url;
        }

        public void setRepos_url(String repos_url) {
            this.repos_url = repos_url;
        }

        public String getEvents_url() {
            return events_url;
        }

        public void setEvents_url(String events_url) {
            this.events_url = events_url;
        }

        public String getReceived_events_url() {
            return received_events_url;
        }

        public void setReceived_events_url(String received_events_url) {
            this.received_events_url = received_events_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isSite_admin() {
            return site_admin;
        }

        public void setSite_admin(boolean site_admin) {
            this.site_admin = site_admin;
        }
    }

    public static class ParentsBean {
        /**
         * url : https://api.github.com/repos/octocat/Hello-World/commits/6dcb09b5b57875f334f61aebed695e2e4193db5e
         * sha : 6dcb09b5b57875f334f61aebed695e2e4193db5e
         */

        private String url;
        private String sha;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSha() {
            return sha;
        }

        public void setSha(String sha) {
            this.sha = sha;
        }
    }

    public static DiffUtil.ItemCallback<Commit> DIFF_CALLBACK = new DiffUtil.ItemCallback<Commit>() {
        @Override
        public boolean areItemsTheSame(@NonNull Commit oldItem, @NonNull Commit newItem) {
            return oldItem.getAuthor().getId() == newItem.getAuthor().getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Commit oldItem, @NonNull Commit newItem) {
            return Objects.equals(oldItem, newItem);
        }
    };
}
