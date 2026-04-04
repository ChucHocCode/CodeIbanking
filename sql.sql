CREATE DATABASE iBanking;
USE iBanking;

CREATE TABLE User(
    Id INT AUTO_INCREMENT PRIMARY KEY,
    UserName VARCHAR(50) UNIQUE,
    PassWord VARCHAR(50),
    role VARCHAR(20)
);

CREATE TABLE Account(
    AccountNumber INT AUTO_INCREMENT PRIMARY KEY,
    Balance DOUBLE,
    user INT, -- Cột này dùng làm khóa ngoại
    -- Sửa lỗi: references User(Id) thay vì userId vì ở trên bạn đặt là Id
    FOREIGN KEY(user) REFERENCES User(Id) 
);

CREATE TABLE History(
    Id INT AUTO_INCREMENT PRIMARY KEY,
    -- Sửa lỗi chính tả: SenderAccount và ReceiverAccount
    SenderAccount INT NOT NULL, 
    ReceiverAccount INT NOT NULL,
    Amount DOUBLE NOT NULL,
    TransactionDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- Sửa lỗi: references Account(AccountNumber) cho đúng tên bảng/cột đã tạo
    FOREIGN KEY(SenderAccount) REFERENCES Account(AccountNumber),
    FOREIGN KEY(ReceiverAccount) REFERENCES Account(AccountNumber)
);

CREATE TABLE SupportTicket(
    Id INT AUTO_INCREMENT PRIMARY KEY,
    UserId INT NOT NULL,
    Subject VARCHAR(100),
    Message TEXT,
    CreateAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- Sửa lỗi: references User(Id) cho khớp với bảng User ở trên
    FOREIGN KEY(UserId) REFERENCES User(Id)
);
drop database iBanking;
select*from User;
select*from Account;
SELECT * FROM History;
SELECT * FROM SupportTicket;