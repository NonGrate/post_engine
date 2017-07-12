from app.popos import Post
from app.popos import ArchivedPost


def get_all():
    return {"name": "archived/all"}


def get_by_id(post_id):
    return {"name": "archived/by_id"}


def revert_post(post_id):
    return {"name": "archived/revert"}


def remove_post(post_id):
    return {"name": "archived/remove"}


def clear():
    return {"name": "archived/clear"}
