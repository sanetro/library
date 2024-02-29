-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 29 Lut 2024, 20:24
-- Wersja serwera: 10.4.22-MariaDB
-- Wersja PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `librarydb`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tbook`
--

CREATE TABLE `tbook` (
  `id` int(11) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `tbook`
--

INSERT INTO `tbook` (`id`, `author`, `isbn`, `status`, `title`) VALUES
(1, 'Jan Kowalski', '123-4567-890A-1B', 0, 'Przygody w nieznanym'),
(2, 'Ewa Nowak', '234-5678-901C-2D', 1, 'Tajemnice kosmosu'),
(3, 'Adam Zielinski', '345-6789-012E-3F', 1, 'Odyseja czasu'),
(4, 'Ola Malinowska', '456-7890-123G-4H', 0, 'Zaginione królestwa'),
(5, 'Marek Wisniewski', '567-8901-234I-5J', 1, 'Echo przeszłości'),
(6, 'Bookser', '543-65562-1-344', 0, 'Added book'),
(7, 'Arthur', '8987-00-7777-72', 0, 'Super Book');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tborrower`
--

CREATE TABLE `tborrower` (
  `id` int(11) NOT NULL,
  `begin` datetime(6) DEFAULT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `returned` datetime(6) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `tborrower`
--

INSERT INTO `tborrower` (`id`, `begin`, `deadline`, `returned`, `book_id`, `user_id`) VALUES
(1, '2024-02-11 19:18:01.000000', '2024-02-25 19:18:01.000000', '2024-02-11 19:21:41.000000', 1, 1),
(2, '2024-02-11 19:18:03.000000', '2024-02-25 19:18:03.000000', '2024-02-11 19:21:42.000000', 2, 1),
(3, '2017-02-07 19:18:05.000000', '2017-03-22 19:18:05.000000', '2024-02-11 19:21:31.000000', 3, 1),
(4, '2024-02-06 19:18:07.000000', '2024-02-25 19:18:07.000000', NULL, 4, 1),
(5, '2024-02-11 19:18:08.000000', '2024-02-25 19:18:08.000000', '2024-02-11 19:23:08.000000', 5, 1),
(6, '2024-02-11 19:18:47.000000', '2024-02-25 19:18:47.000000', NULL, 6, 2),
(7, '2024-02-11 19:22:07.000000', '2024-02-25 19:22:07.000000', NULL, 1, 1),
(8, '2024-02-29 20:15:04.000000', '2024-03-14 20:15:04.000000', NULL, 7, 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tuser`
--

CREATE TABLE `tuser` (
  `id` int(11) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `tuser`
--

INSERT INTO `tuser` (`id`, `login`, `name`, `password`, `surname`) VALUES
(1, 'admin', 'Adminowsky', '21232f297a57a5a743894a0e4a801fc3', 'Kowalska'),
(2, 'user', 'Userowsky', 'ee11cbb19052e40b07aac0ca060c23ee', 'Nowak');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `tbook`
--
ALTER TABLE `tbook`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `tborrower`
--
ALTER TABLE `tborrower`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKq5ehxuums28q8j0axiqqe2yu5` (`book_id`),
  ADD KEY `FK5avmsv8ihrwjs6jmilag8wbbu` (`user_id`);

--
-- Indeksy dla tabeli `tuser`
--
ALTER TABLE `tuser`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_fxd42ti0c4d7jrttsf8h0lvvn` (`login`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `tbook`
--
ALTER TABLE `tbook`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT dla tabeli `tborrower`
--
ALTER TABLE `tborrower`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT dla tabeli `tuser`
--
ALTER TABLE `tuser`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `tborrower`
--
ALTER TABLE `tborrower`
  ADD CONSTRAINT `FK5avmsv8ihrwjs6jmilag8wbbu` FOREIGN KEY (`user_id`) REFERENCES `tuser` (`id`),
  ADD CONSTRAINT `FKq5ehxuums28q8j0axiqqe2yu5` FOREIGN KEY (`book_id`) REFERENCES `tbook` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
