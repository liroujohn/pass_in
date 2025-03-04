CREATE TABLE check_ins (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    attendee_id VARCHAR(255) NOT NULL,
    CONSTRAINT check_ins_attendee_id_fkey FOREIGN KEY (attendee_id) REFERENCES attendees(id) ON DELETE RESTRICT ON UPDATE CASCADE
);