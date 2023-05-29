import psycopg2
import random

conn = psycopg2.connect(
    dbname="postgres",
    user="postgres",
    password="postgres",
    host="localhost",
    port="5432"
)

cur = conn.cursor()

cur.execute("""
    DROP TABLE wear;
""")

conn.commit()

cur.close()
conn.close()