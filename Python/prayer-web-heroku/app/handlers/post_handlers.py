from flask import jsonify, request

from app.database import flask_post_database as database


def post_all():
    posts = database.get_all()
    return jsonify(posts)


def post_by_id(post_id):
    posts = database.get_by_id(int(post_id))
    return jsonify(posts)


def post_add():
    new_id = database.add_post(request.form['content'], request.form['color'])
    return jsonify({"id": new_id})


def post_like(post_id):
    post = database.like_post(int(post_id))
    return jsonify(post)


def post_close(post_id):
    new_post_id = database.close_post(int(post_id))
    return jsonify({"id": new_post_id})


def post_remove(post_id):
    success = database.remove_post(int(post_id))
    return jsonify(success), 304


def post_clear():
    success = database.clear()
    return jsonify(success), 304
