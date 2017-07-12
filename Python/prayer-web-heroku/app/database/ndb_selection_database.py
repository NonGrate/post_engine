from popos import Selection


def get_by_user(user_id):
    query = Selection.query(Selection.user_id == user_id).order(-Selection.date).fetch()
    return query


def get_by_post(post_id):
    query = Selection.query(Selection.post_id == post_id).order(-Selection.date).fetch()
    return query


def get_by_id(selection_id):
    return Selection.get_by_id(selection_id)


def add_selection(user_id, post_id):
    selection = Selection(user_id=user_id, post_id=post_id)
    key = selection.put()

    return key.id()


def remove_selection(user_id, post_id):
    selection = Selection.query(Selection.post_id == post_id, Selection.user_id == user_id).fetch()
    key = selection.key().delete()

    # TODO return true if entity was removed
    return True


def clear():
    return False
