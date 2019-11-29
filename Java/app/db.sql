PRAGMA ENCODING='UTF-8';

CREATE TABLE "category" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"color"	INTEGER NOT NULL
);

CREATE TABLE "todoitem" (
	"id"	    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"title"	    TEXT NOT NULL,
	"completed"	INTEGER NOT NULL DEFAULT 0,
	"category"	INTEGER,

	FOREIGN KEY("category") REFERENCES "category"("id")
);

CREATE TABLE "options" (
	"id"	    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"opt_key"	TEXT NOT NULL,
	"opt_value"	TEXT NOT NULL
);

INSERT INTO category (color) VALUES
    ('#3c40c6'),
    ('#05c46b'),
    ('#ff3f34'),
    ('#ffa801'),
    ('#485460'),
    ('#ffffff');