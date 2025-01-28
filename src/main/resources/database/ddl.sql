CREATE TYPE department_enum AS ENUM (
    'BILGISAYAR_MUHENDISLIGI',
    'ELEKTRIK_MUHENDISLIGI',
    'SOSYOLOJI',
    'ISLETME'
);

CREATE TABLE students (
                          id SERIAL PRIMARY KEY,
                          student_number VARCHAR(20) UNIQUE NOT NULL,
                          first_name VARCHAR(50) NOT NULL,
                          last_name VARCHAR(50) NOT NULL,
                          department department_enum NOT NULL,
                          enrollment_date DATE NOT NULL
);

