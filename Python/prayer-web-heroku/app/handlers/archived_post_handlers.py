from flask import jsonify

from app.database import flask_archived_post_database as database


def archived_all():
    posts = database.get_all()
    return jsonify(posts)


def archived_by_id(post_id):
    posts = database.get_by_id(int(post_id))
    return jsonify(posts)


def archived_revert(post_id):
    new_post_id = database.revert_post(int(post_id))
    return jsonify({"id": new_post_id})


def archived_remove(post_id):
    success = database.remove_post(int(post_id))
    return success, 304


def archived_clear():
    success = database.clear()
    return success, 304
