from app.popos import Post
from app.popos import ArchivedPost
import sqlite3


def get_all():
    connection = SQLiteConnection()
    posts = connection.execute("SELECT rowid AS id, content, rating, color, date FROM posts").fetchall()
    connection.disconnect()
    return posts


def get_by_id(post_id):
    connection = SQLiteConnection()
    post = connection.execute("SELECT rowid AS id, content, rating, color, date FROM posts WHERE rowid=?", (post_id,)).fetchone()
    connection.disconnect()
    return post


def add_post(content, color):
    connection = SQLiteConnection()
    last_row_id = connection.execute("INSERT INTO posts(content, rating, color) VALUES (?, ?, ?)", (content, 0,
                                                                                                    color,)).lastrowid
    connection.commit()
    connection.disconnect()
    return {"id": last_row_id}


def like_post(post_id):
    connection = SQLiteConnection()
    last_row_id = connection.execute("UPDATE posts SET rating=rating+1 WHERE rowid=?", (post_id,)).lastrowid
    connection.commit()
    connection.disconnect()
    return get_by_id(post_id)


def archive_post(post_id):
    connection = SQLiteConnection()
    post = get_by_id(post_id)
    remove_post(post_id)
    last_row_id = connection.execute("INSERT INTO posts(rowid, content, rating, color, date) VALUES (?, ?, ?, ?, ?)",
                         (post.id, post.content, post.rating, post.color, post.date)).lastrowid
    connection.commit()
    connection.disconnect()
    return {"id": last_row_id}


def remove_post(post_id):
    connection = SQLiteConnection()
    connection.execute("DELETE FROM posts WHERE rowid=?", (post_id,))
    connection.commit()
    connection.disconnect()
    return "", 200


def clear():
    connection = SQLiteConnection()
    connection.execute("DELETE FROM posts")
    connection.commit()
    connection.disconnect()
    return "", 200


class SQLiteConnection:
    sqlite = None
    cursor = None

    def __init__(self):
        # Connect to the DB file and prepare a table for work
        self.sqlite = sqlite3.connect('posts.db')
        self.sqlite.row_factory = self.dict_factory
        self.cursor = self.sqlite.cursor()

    def disconnect(self):
        self.cursor.close()
        self.sqlite.close()

    def execute(self, query, parameters=()):
        return self.cursor.execute(query, parameters)

    def commit(self):
        self.sqlite.commit()

    @staticmethod
    def dict_factory(cursor, row):
        d = {}

        for idx, col in enumerate(cursor.description):
            d[col[0]] = row[idx]
        return d


connection = SQLiteConnection()
connection.execute("CREATE TABLE IF NOT EXISTS posts (content TEXT, rating INTEGER, color INTEGER, date DATETIME DEFAULT CURRENT_TIMESTAMP)")
connection.execute("CREATE TABLE IF NOT EXISTS archived_posts (content TEXT, rating INTEGER, color INTEGER, date DATETIME, update_date DATETIME DEFAULT CURRENT_TIMESTAMP)")
connection.disconnect()
