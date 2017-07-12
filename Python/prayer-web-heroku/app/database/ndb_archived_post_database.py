from popos import Post
from popos import ArchivedPost


def get_all():
    query = ArchivedPost.query().order(-ArchivedPost.date).fetch()
    return query


def get_by_id(post_id):
    return ArchivedPost.get_by_id(post_id)


def revert_post(post_id):
    archived_post = get_by_id(post_id)
    post = Post(archived_post)
    key = post.put()
    archived_post.key().delete()

    return key.id()


def remove_post(post_id):
    archived_post = get_by_id(post_id)
    archived_post.key().delete()

    return True


def clear():
    return False
