from app.popos import Selection


def get_by_user(user_id):
    return {"name": "selection/by_user"}


def get_by_post(post_id):
    return {"name": "selection/by_post"}


def get_by_id(selection_id):
    return {"name": "selection/by_id"}


def add_selection(user_id, post_id):
    return {"name": "selection/add"}


def remove_selection(user_id, post_id):
    return {"name": "selection/remove"}


def clear():
    return {"name": "selection/clear"}
