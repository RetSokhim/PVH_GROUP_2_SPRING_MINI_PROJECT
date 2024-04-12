CREATE TABLE IF NOT EXISTS user_tb(
    user_id SERIAL PRIMARY KEY ,
    email VARCHAR(100) UNIQUE NOT NULL ,
    password VARCHAR(300) NOT NULL ,
    profile_image VARCHAR(300) NOT NULL
);
CREATE TABLE IF NOT EXISTS otps_tb(
    otps_id SERIAL PRIMARY KEY,
    otps_code INTEGER ,
    issued_at TIMESTAMP,
    expiration TIMESTAMP,
    verify INTEGER DEFAULT 0,
    user_id INT REFERENCES user_tb(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS categories_tb(
    category_id SERIAL PRIMARY KEY ,
    name VARCHAR(300) NOT NULL ,
    description TEXT,
    user_id INT REFERENCES user_tb(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE IF NOT EXISTS expense_tb(
    expense_id SERIAL PRIMARY KEY ,
    amount INTEGER,
    description VARCHAR(200) NOT NULL,
    date TIMESTAMP,
    user_id INT REFERENCES user_tb(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    category_id INT REFERENCES categories_tb(category_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- UPDATE otps_tb SET verify = 382510 WHERE otps_code = 382510;
-- SELECT verify FROM otps_tb WHERE otps_code = 670065