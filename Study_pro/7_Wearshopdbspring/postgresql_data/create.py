import psycopg2
import random

names = ["Майка", "Шорты", "Трико", "Спортивки", "Рубашка", "Пальто", "Кепка", "Шапка", "Футболка", "Бомбер"]
sizes = ["S", "XS", "M", "L", "L", "S", "XL", "XXL", "M", "XXXL"]

conn = psycopg2.connect(
    dbname="postgres",
    user="postgres",
    password="postgres",
    host="localhost",
    port="5432"
)

cur = conn.cursor()

cur.execute("""
    CREATE TABLE wear (
        id SERIAL PRIMARY KEY,
        name VARCHAR(50),
        size VARCHAR(50),
        public_date VARCHAR(20),
        cost INTEGER
    )
""")

for i in range(len(names):
    name = names[i]
    size = sizes[i]
    public_date = "{}/{}/{}".format(random.randint(1, 31), random.randint(1, 12), random.randint(2010, 2023))
    cost = random.randint(1000, 100000)
    cur.execute("""
        INSERT INTO wear (name, size, public_date, cost)
        VALUES (%s, %s, %s, %s)
    """, (name, size, public_date, cost))

conn.commit()

cur.close()
conn.close()