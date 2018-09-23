package model;

import java.util.*;

public class PostsList {

    private LinkedList<Post> posts;

    public PostsList() {
        posts = new LinkedList<>();
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
                .orElse(new Homework(Homework.noHomeworkId, "There is no homework yet", "", "", MyDate.now(), MyDate.now(), new ArrayList<>(), 0, new ArrayList<>(), false));
    }

    public Discussion getFirstDiscussion() {
        return posts.stream().filter(p -> p instanceof Discussion).map(h -> (Discussion) h).findFirst()
                .orElse(new Discussion(Discussion.noDiscussionId, "There are no discussions yet", "", "",SpecialType.NORMAL, MyDate.now(), new ArrayList<>()));
    }

    public Announcement getFirstAnnouncement() {
        return posts.stream().filter(p -> p instanceof Announcement).map(h -> (Announcement) h).findFirst()
                .orElse(new Announcement(Announcement.noAnnouncementId, "There are no announcements yet", "", MyDate.now(), SpecialType.NORMAL, new ArrayList<>(), MyDate.now()));
    }

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

    public Discussion addComment(DiscussionComment comment) {
        for (Post e : posts) {
            if (e instanceof Discussion)
                if(e.getPostId().equals(comment.getDiscussionid())){
                    ((Discussion) e).addComment(comment);
                    return (Discussion) e;
                }
        }
        return null;
    }
}
