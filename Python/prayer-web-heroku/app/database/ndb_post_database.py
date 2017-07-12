from popos import Post
from popos import ArchivedPost


def get_all():
    query = Post.query().order(-Post.date).fetch()
    return query


def get_by_id(post_id):
    return Post.get_by_id(post_id)


def add_post(content, color):
    post = Post(content=content, color=color, rating=0)
    key = post.put()

    return key.id()


def like_post(post_id):
    post = get_by_id(post_id)
    post.rating += 1
    post.put()

    return get_by_id(post_id)


def close_post(post_id):
    post = get_by_id(post_id)
    archived_post = ArchivedPost(post)
    key = archived_post.put()
    post.key().delete()

    return key.id()


def remove_post(post_id):
    post = get_by_id(post_id)
    post.key().delete()

    return True


def clear():
    return False
