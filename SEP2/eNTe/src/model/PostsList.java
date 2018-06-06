package model;

import java.util.*;

public class PostsList {

    private LinkedList<Post> posts;

    public PostsList() {
        posts = new LinkedList<>();
    }

    public Post getNextPost() {
        //i don't like this method :/
        return posts.removeFirst();
    }

    public Post getFirstPost() {
        return posts.getFirst();
    }

    public void add(Post post) {
        posts.add(post);
    }

    public void addAll(List<Post> list) {
        posts.addAll(list);
    }

    public ArrayList<Post> getAll() {
        return new ArrayList<>(posts);
    }

    public Homework getFirstHomework() {
        return posts.stream().filter(p -> p instanceof Homework).map(h -> (Homework) h).findFirst()
                .orElse(new Homework(Homework.noHomeworkId, "There are no homeworks yet", "", "", MyDate.now(), MyDate.now(), new ArrayList<>(), 0, new ArrayList<>(), false));
    }

//    public Discussion getFirstDiscussion() {
//        return posts.stream().filter(p -> p instanceof Discussion).map(h -> (Discussion) h).findFirst()
//                .orElse(new Discussion(Discussion.noDiscussionId, "There are no discussions yet", "", "", MyDate.now()));
//    }

    public void deletePost(Post post) {
        posts.remove(post);
    }

    public void editPost(Post post) {
        for (int i = 0, postsSize = posts.size(); i < postsSize; i++) {
            Post u = posts.get(i);
            if (u.getPostId().equals(post.getPostId())) {
                posts.set(i, post);
            }
        }
    }

    public void clear() {
        posts.clear();
    }

    public Post getPostById(String id) {
        return posts.stream().filter(u -> u.getPostId().equals(id)).findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostsList)) return false;
        PostsList postsList = (PostsList) o;
        return Objects.equals(posts, postsList.posts);
    }
}
