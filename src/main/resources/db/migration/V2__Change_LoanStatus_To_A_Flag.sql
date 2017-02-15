ALTER TABLE BOOK
ADD available BIT DEFAULT FALSE;

UPDATE BOOK
SET available = TRUE
WHERE loan_status = 'Available';

ALTER TABLE BOOK  DROP COLUMN loan_status;