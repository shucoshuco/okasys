db.createUser(
  {
    user: "dbuser1",
    pwd: "dbuser1",
    roles: [
      {
        role: "readWrite",
        db: "okadb"
      }
    ]
  }
);

db.createUser(
  {
    user: "dbgraylog",
    pwd: "dbgraylog",
    roles: [
      {
        role: "readWrite",
        db: "graylogdb"
      }
    ]
  }
);

db = db.getSiblingDB('okadb');

db.createCollection('cells');

db.cells.insertMany([
  {
    "name": "Kisses",
    "description": "Kisses whatever",
    "level": 1,
    "imageUrl": "http://host.com/image.png",
    "items": 0
  }
]);
