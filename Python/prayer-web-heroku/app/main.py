from flask import Flask
from app.popos import JsonEncoder

app = Flask(__name__)
app.json_encoder = JsonEncoder

from app.handlers import user_handlers
from app.handlers import post_handlers
from app.handlers import selection_handlers
from app.handlers import archived_post_handlers

@app.route('/')
def main():
    return "Hello World!"


@app.route('/help')
def help():
    return 'Help, I\'m inside this green robot!'

app.add_url_rule('/help', view_func=help)
# posts
app.add_url_rule('/post/all', view_func=post_handlers.post_all)
app.add_url_rule('/post/<int:post_id>', view_func=post_handlers.post_by_id)
app.add_url_rule('/post/add', view_func=post_handlers.post_add, methods=['POST'])
app.add_url_rule('/post/like/<int:post_id>', view_func=post_handlers.post_like)
app.add_url_rule('/post/archive/<int:post_id>', view_func=post_handlers.post_archive)
app.add_url_rule('/post/remove/<int:post_id>', view_func=post_handlers.post_remove)
app.add_url_rule('/post/clear', view_func=post_handlers.post_clear, methods=['POST'])
# archived posts
app.add_url_rule('/post/archived/all', view_func=archived_post_handlers.archived_all)
app.add_url_rule('/post/archived/<int:post_id>', view_func=archived_post_handlers.archived_by_id)
app.add_url_rule('/post/archived/revert/<int:post_id>', view_func=archived_post_handlers.archived_revert)
app.add_url_rule('/post/archived/remove/<int:post_id>', view_func=archived_post_handlers.archived_remove)
app.add_url_rule('/post/archived/clear', view_func=archived_post_handlers.archived_clear, methods=['POST'])
# users
app.add_url_rule('/user/all', view_func=user_handlers.user_all)
app.add_url_rule('/user/<int:post_id>', view_func=user_handlers.user_by_id)
app.add_url_rule('/user/add', view_func=user_handlers.user_add, methods=['POST'])
app.add_url_rule('/user/remove/<int:post_id>', view_func=user_handlers.user_remove)
app.add_url_rule('/user/clear', view_func=user_handlers.user_clear, methods=['POST'])
# selections
app.add_url_rule('/selection/by_user/<int:post_id>', view_func=selection_handlers.selection_by_user)
app.add_url_rule('/selection/by_post/<int:post_id>', view_func=selection_handlers.selection_by_post)
app.add_url_rule('/selection/add', view_func=selection_handlers.selection_add, methods=['POST'])
app.add_url_rule('/selection/remove', view_func=selection_handlers.selection_remove, methods=['POST'])
app.add_url_rule('/selection/clear', view_func=selection_handlers.selection_clear, methods=['POST'])

if __name__ == '__main__':
    app.run(debug=True)
