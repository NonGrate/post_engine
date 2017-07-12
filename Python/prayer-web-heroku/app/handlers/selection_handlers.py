from flask import jsonify

from app.database import flask_selection_database as database


def selection_by_user(user_id):
    posts = database.get_by_user(int(user_id))
    return jsonify(posts)


def selection_by_post(post_id):
    posts = database.get_by_post(int(post_id))
    return jsonify(posts)


def selection_add(selection):
    new_id = database.add_selection(user_id=selection.user_id, post_id=selection.post_id)
    return jsonify({"id": new_id})


def selection_remove(selection):
    success = database.remove_selection(user_id=selection.user_id, post_id=selection.post_id)
    return success, 304


def selection_clear():
    success = database.clear()
    return success, 304
