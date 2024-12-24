-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 20 déc. 2024 à 12:31
-- Version du serveur : 8.0.31
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `autoecole`
--

DELIMITER $$
--
-- Procédures
--
DROP PROCEDURE IF EXISTS `genererComptes`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `genererComptes` ()   BEGIN
    DECLARE done INT DEFAULT 0;
    DECLARE compteId INT;
    DECLARE eleveId INT;
    DECLARE eleveNom VARCHAR(50);
    DECLARE elevePrenom VARCHAR(50);
    DECLARE moniteurId INT;
    DECLARE moniteurNom VARCHAR(50);
    DECLARE moniteurPrenom VARCHAR(50);
    DECLARE suffixe INT DEFAULT 1;
    DECLARE loginUnique VARCHAR(50);

    -- Déclaration des curseurs
    DECLARE eleveCursor CURSOR FOR SELECT CodeEleve, Nom, Prenom FROM eleve;
    DECLARE moniteurCursor CURSOR FOR SELECT CodeMoniteur, Nom, Prenom FROM moniteur;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    -- Traitement pour les élèves
    OPEN eleveCursor;
    eleve_loop: LOOP
        FETCH eleveCursor INTO eleveId, eleveNom, elevePrenom;
        IF done THEN
            LEAVE eleve_loop;
        END IF;

        -- Génération du login
        SET @login = CONCAT(LEFT(elevePrenom, 3), LEFT(eleveNom, 2));
        SET loginUnique = @login;

        -- Vérification des doublons
        WHILE EXISTS (SELECT 1 FROM Compte WHERE login = loginUnique) DO
            SET loginUnique = CONCAT(@login, suffixe);
            SET suffixe = suffixe + 1;
        END WHILE;

        -- Insertion dans la table Compte
        INSERT INTO Compte (login, motDePasse) 
        VALUES (loginUnique, CONCAT('test', LAST_INSERT_ID()));

        -- Récupération de l'ID de compte nouvellement créé
        SET compteId = LAST_INSERT_ID();

        -- Mise à jour de l'élève avec le numéro de compte
        UPDATE eleve SET numCompte = compteId WHERE CodeEleve = eleveId;
    END LOOP;
    CLOSE eleveCursor;

    -- Réinitialisation du signal pour les moniteurs
    SET done = 0;
    SET suffixe = 1;  -- Réinitialisation du suffixe

    -- Traitement pour les moniteurs
    OPEN moniteurCursor;
    moniteur_loop: LOOP
        FETCH moniteurCursor INTO moniteurId, moniteurNom, moniteurPrenom;
        IF done THEN
            LEAVE moniteur_loop;
        END IF;

        -- Génération du login
        SET @login = CONCAT(LEFT(moniteurPrenom, 3), LEFT(moniteurNom, 2));
        SET loginUnique = @login;

        -- Vérification des doublons
        WHILE EXISTS (SELECT 1 FROM Compte WHERE login = loginUnique) DO
            SET loginUnique = CONCAT(@login, suffixe);
            SET suffixe = suffixe + 1;
        END WHILE;

        -- Insertion dans la table Compte
        INSERT INTO Compte (login, motDePasse) 
        VALUES (loginUnique, CONCAT('test', LAST_INSERT_ID()));

        -- Récupération de l'ID de compte nouvellement créé
        SET compteId = LAST_INSERT_ID();

        -- Mise à jour du moniteur avec le numéro de compte
        UPDATE moniteur SET numCompte = compteId WHERE CodeMoniteur = moniteurId;
    END LOOP;
    CLOSE moniteurCursor;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `CodeCategorie` int NOT NULL,
  `Libelle` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Prix` double(5,2) NOT NULL,
  PRIMARY KEY (`CodeCategorie`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`CodeCategorie`, `Libelle`, `Prix`) VALUES
(1, 'Automobile', 34.95),
(2, 'Poids lourd', 43.00),
(3, 'Bateau', 51.25),
(4, 'Moto', 38.15),
(5, 'Transport en commun', 40.50);

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

DROP TABLE IF EXISTS `compte`;
CREATE TABLE IF NOT EXISTS `compte` (
  `numCompte` int NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `motDePasse` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `statut` int NOT NULL,
  PRIMARY KEY (`numCompte`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`numCompte`, `login`, `motDePasse`, `statut`) VALUES
(1, 'GweGn', '590c9f8430c7435807df8ba9a476e3f1295d46ef210f6efae2043a4c085a569e', 0),
(2, 'MazMa', '1b4f0e9851971998e732078544c96b36c3d01cedf7caa332359d6f1d83567014', 0),
(3, 'OmeOs', '60303ae22b998861bce3b28f33eec1be758a213c86c93c076dbe9f558c11c752', 0),
(4, 'SopSp', 'fd61a03af4f77d870fc21e05e7e80678095c92d808cfb3b5c279ee04c74aca13', 0),
(5, 'ThéTo', 'a4e624d686e03ed2767c0abd85c14426b0b1157d2ce81d27bb4fe4f6f01d688a', 0),
(6, 'PisPo', 'a140c0c1eda2def2b830363ba362aa4d7d255c262960544821f556e16661b6ff', 0),
(7, 'CélCa', 'ed0cb90bdfa4f93981a7d03cff99213a86aa96a6cbcf89ec5e8889871f088727', 0),
(8, 'RomRa', 'bd7c911264aae15b66d4291b6850829aa96986b1d3ead34d1fdbfef27056c112', 0),
(9, 'BruBa', '1f9bfeb15fee8a10c4d0711c7eb0c083962123e1918e461b6a508e7146c189b2', 0),
(10, 'TanTo', 'b4451034d3b6590060ce9484a28b88dd332a80a22ae8e39c9c5cb7357ab26c9f', 0),
(11, 'VérVe', 'ec2738feb2bbb0bc783eb4667903391416372ba6ed8b8dddbebbdb37e5102473', 0),
(12, 'ThoTa', '744ea9ec6fa0a83e9764b4e323d5be6b55a5accfc7fe4c08eab6a8de1fca4855', 0),
(13, 'GonGh', 'a98ec5c5044800c88e862f007b98d89815fc40ca155d6ce7909530d792e909ce', 0),
(14, 'BruBu', '166fb78f0f44d271a2d9065272a67ba373c3266b59d85847c02ef695af0cbf3f', 0),
(15, 'CléCa', '40cca5cc13abf91c7d5a72c0aea9bcbea4108946e67f24c0c23003cbf307efa2', 0),
(16, 'ChaCh', 'ebb39b342baead7aa52c0bcd6c0d4ba061b42f3a9dd6bafa2407a096b91b2450', 0),
(17, 'BasBr', '8ffd063b93a29f84389a635552740a9f0a7234169994158fb19692f5964dd7f5', 0),
(18, 'FerFa', '813e41d4092656716cb0b46a1e5002857066cdaef8decf182ae15abf0b43b8d5', 0),
(19, 'LazLa', 'b3c0e5febe1ec8875cd4a06fa4a99abf270de3f131d83a65f897322edbc12aec', 0),
(20, 'RolRi', '840b1bf550a873a1dbed1381abe379cb9f1e76067b6de54bcd37367ce6ca3c0a', 0),
(21, 'PauPo', '946cc198869790373cd8424cd9073e9e29aaa17b6f6a6ec55b38110cae856385', 0),
(22, 'ThéTa', 'aefd57c8c2afdaad0a5352b0ce87131a85a08f5c87a87f166f0ce1e213f4c0fd', 0),
(23, 'MilMo', '759cfde265aaddb6f728ed08d97862bbd9b56fd39de97a049c640b4c5b70aac9', 0),
(24, 'CloCa', '4e758d4760a6cffc347cdb45f0966d20f481bad806731c4c0e44f21cf9d90bb5', 0),
(25, 'CorCl', '65b440e2e0a921e4a9adc14445374d498a95d05f299d791548d6838eb0ae65df', 0),
(26, 'FélFe', '0342840f6340d15691f4be1c0e0157fb0983992c4f436c18267d41dbe6bb74a2', 0),
(27, 'RitRi', 'ebe9f9116525a660751600a7f897df4b03d45b5dfc17ae36a9dd33b34f9849b4', 0),
(28, 'CléCa1', 'a45c66cfc88915d1fc91bf998ea726b34c530b63b38984f5a0f313766b799808', 0),
(29, 'AmaAn', '94384f0790043d99b7b85cb0f518eccdbfdf066b72324e3d996c4d0c738b65b5', 0),
(30, 'ScaSc', '15dfe842cd5eb4a591465c8e8927e16cd2b16e3ca7b4312933d763882f38270f', 0),
(31, 'AmaAn2', '98b96436416b60ef629ebe764dc75bb2d3052145829b24aa43a5168aabf0942d', 0),
(32, 'BriBa', '5261b37d94728f221fd8d25e9f9eff5584316d8a990372fe9282aa7e6d6710e9', 0),
(33, 'FioFu', '91d3510c0672508f97b00f917e513e4dc2f57dd6e53cab95c28eebdc3f88a108', 0),
(34, 'MélMe', '76226c5fd1ad06a945da03fc6ff776e7681c33661925f3d397ac8e18b4eaf564', 0),
(35, 'FerFa3', 'bd7c74fc6804bfd3bab881c04f81236c862b06606a8228dfc29174d05eb9e6fb', 0),
(36, 'VérVo', '43e3603bfec2885d2184d8534a9a68c65261792f5fbb6b39aab287b4b86eeaf0', 0),
(37, 'PasPe', '66643f8964abd994a78b8644ecdf19ddbb7fce48bcdd08dd91d3b0aa2d8476fc', 0),
(38, 'ChaCo', 'cefaef766de65a7384333fb99cc21c0e65e04e53df10d4439d70eb70b505a02d', 0),
(39, 'SabSt', 'e2ed63496eb5fd475c93110f8d05d3e079b69e59bd84f30b5e1b8cb8d3d22ca5', 0),
(40, 'MarMi', '62844dae9249e8443c13aee33f15743fa254498031e3949727358ce7c8168d97', 0),
(41, 'PiePa', 'f3dc2b17fd23faab2deb6216ac9033e3c2670327abc6166794c16ce9437bc4b4', 0),
(42, 'FloFo', 'df332d413607690aeb4b8f21882ff0ecb3cf9cea9ddb2328ee6c85999de206a5', 0),
(43, 'CunCa', '338c0605bab38900480ebcc7fb0651426cc26cd1732579f04b47f779a8962d83', 0),
(44, 'CorCa', '8774f52c0f1cb920ce6470faf2dce0a5555484140fdce6e800bc482d27e0b21e', 0),
(45, 'TerTo', 'ad59ed77bfd3757a879332e95644e322f0808bc94c6217a3490f85f176448b7d', 0),
(46, 'ChrCa', '9291e045de7cfcc8dc5ac645e166d5f07b428f56872bfd1bd5c18ba707744f7e', 0),
(47, 'SimSc', 'df4883ef2cb0b7900a8401caf33c9a57fe9526d2da728347d28f5e6589333e60', 0),
(48, 'LéoLi', 'd29c18776f9b104b63409a0b617a4f4cbcb9fe8c512bd78d4abeb3bee5d1562b', 0),
(49, 'BénBi', '9640fa8ac42bbdbca83163ed2b7ee9ecff3b1dc0712514b0b1377ddc3f2fa817', 0),
(50, 'UrsUo', '83bc3329430af10d70f05217525b5c3970800047027e4682fabc1d69bd0fef74', 0),
(51, 'CamCa', '9ca6333ba92ea4aaa3e4c972c4c9301f2931154b73373a25b849fce8fd4e16c9', 0),
(52, 'UllUc', '29d75d258e03dc8758382d6bd793414670860125c848861224d1e8cc0e2899c6', 0),
(53, 'ConCa', '0c117c58f1c3fbb490946d1be8dc28464cdae8814531d227bac8c34d5c48e2ca', 0),
(54, 'PriPr', '8b0fee57428d2465b1a5eeb83c4088cd14d3a8294e90fd9a0a4d374041bbe0e0', 0),
(55, 'PatPe', 'c2c5d8026826fea0119924d86391e5f3fcf8e08801e4d75017ded4c212db9408', 0),
(56, 'SteSp', 'd08b55c5f8ac968e2477a0ea68308859f19a1f8adce5cc760f92f443970f90e3', 0),
(57, 'PatPa', '264f4e176c3edb611154c6da9259369f8e987c23dca75ab2f2505a2754f242c3', 0),
(58, 'ThiTe', '2d5d1edafdda52004bca8c9dab84121a85056ba8ab93075477f0902816b08267', 0),
(59, 'ZoéZu', '4aa32e0e703c31dbfdcc847034cdefd5aec24edd735a58a30da33f1a28684145', 0),
(60, 'ArmAr', '0748b1829e7d626b21bab9e50cb6b88730a56e8546a45b806c6cbbada328cd56', 0),
(61, 'TerTr', 'eea12de80a09eaae4172d0f8ae0243d2d1629e3abe88ac839e790e22fea12f70', 0),
(62, 'ChrCa4', '048f4abb501a20506550ed860a0d78f533aafdebaad3e79ae9eb002ce103c297', 0),
(63, 'OrnOs', 'e3a166423764c3f6e15545c0be4d7af20fa5e16fba3533a7512f509700cc7b07', 0),
(64, 'ThéTr', '45fc3057d389c5cba1055af20f36a9422291c7f73a639bb0e8f76103bdf0b490', 0),
(65, 'PolPo', '9d11b57f078cac8dfad0ca3f980a077519a3f78efeb3f909c3c6a081bba5c2c1', 0),
(66, 'CarCa', 'f242940ce161017af98b697b12de1402a38bae8c94007bbd7f81a042cb381eef', 0),
(67, 'PiePe', 'c644a45ba4b14fbb202020f0e231dad5d479074c3b3384003a90d3df320f5ea7', 0),
(68, 'VinVi', 'b85d95e12c6de414c7c7a115bac0926b81be9b27cd8628bf78352729c0df5d58', 0),
(69, 'CélCi', '01775e9d78f43286f1b9283cfd5ac88a1b916f1f14f05be947f18ad15458f6bd', 0),
(70, 'PauPo5', '9f2d0ea5850156f702bc2d2b422e82b82d45c0f41786dd78b26f70021ff7c90b', 0),
(71, 'MarMa', 'fe0e5ebf97c5941df65b903f8a7b41f9955745d62db126c5c2682df97f5b3a4f', 0),
(72, 'AlbAb', 'fa2c5276894d911e7695b121482104a4bc180d6b65ddbb357fa48e22f03ea608', 0),
(73, 'ValVa', 'bb35037ab5adf4edc0dbb247dc70a048a94c7c13cdd5d879973c9e1fb5af5185', 0),
(74, 'GérBé', '0bfd1940f3f32fec5b0e08dc27421bbfe2dac684ec2c2783ae4798a22bf0e320', 1),
(75, 'PieAm', '203259a5189c084e7df48a1fe18b39748677ac0145d986ca78abe71248b0b7cc', 1),
(76, 'OliCa', '7662dcf2302ebfbd44733a8ca600b446000be2d503a7825f44a67ddab459886d', 1),
(77, 'RégEz', '0c8edb478e34766c9165f4fe57b41e8f51a076c7d5d3c054f42067549562b121', 1),
(78, 'testEleve', 'db246794ebd17067ef0e23b126542812ba9b3eb653edac7e1e0a0a91d6655b30', 0),
(79, 'testMoniteur', '404b2da75ab944272cb2b802162930e83328e7546b88c9b1d9d2b720600cc18d', 1),
(80, 'HollowPredator', 'b21b17b4ecc60cdbfa7b8dfd13b611a862f8c0a8416f0a0958356cffef8e26f2', 1),
(81, 'Succes', 'fc43ebef8e2aba3998482a6bb5fd5f4da2e64e6ccfadddf8020e867686b8c1fa', 1),
(83, 'SuccesEleve', 'fc43ebef8e2aba3998482a6bb5fd5f4da2e64e6ccfadddf8020e867686b8c1fa', 0),
(84, 'tryInscMoniteur', '4e3ec582be83c5a944dab5fed722e080940ecc057d2503f163c1f87827ce1598', 1),
(85, 'tryInscEleve', '587b7ba622426bf6e859e9a65a911b35ca0ba29c37d0331e3b820a71ca476790', 0),
(86, 'tagrandmere', '2d2222d77516ff95a553b9e5d71c643c0509b679c0c5c339e348ff24d81a24ba', 0),
(87, 'toto', 'cce66316b4c1c59df94a35afb80cecd82d1a8d91b554022557e115f5c275f515', 0),
(88, 'zebbb', '0357513deb903a056e74a7e475247fc1ffe31d8be4c1d4a31f58dd47ae484100', 0),
(92, 'teub', '0357513deb903a056e74a7e475247fc1ffe31d8be4c1d4a31f58dd47ae484100', 0),
(93, 'tomlachienneté', '35ad7fb1814efe880e4542226fd5223958a18c4a6d0a653ee307679a207d437b', 0),
(96, 'tets', 'dc016d2fdd6d93b94aa0fe8f32cac862c18616bd85f3412b8b171dba5e39bc01', 0),
(97, 'test', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', 0),
(98, 'muscat', 'a9423b17e82a35bfb30d60f1be4ca4c53d0959276144e4482d633f74ce591ec5', 0),
(102, 'tom', '737fff1bfce0ed11a4b1041ff488f3979d6e25ba68313882a8941d09036c5d67', 0),
(103, 'soso', 'ab1e6be2000a7c9c3d390282749f531bbcf6a377c41a5fe0261481e1911d8fce', 0),
(104, 'justin', '102cf10b5286bad9fcfe5e275ace3ddd7dcc23931fb0ca93dc223daf9877cabd', 0),
(105, 'este', 'b3aa33b34e1b14e4cf3eded0cac6717a8385caa1e948448d0055e59eff4def69', 0),
(106, 'pierre', '1f47b0927a25c7c6f55ba1640b4893e2566f7764cfa1c3ed26945dfd7a825569', 0);

-- --------------------------------------------------------

--
-- Structure de la table `eleve`
--

DROP TABLE IF EXISTS `eleve`;
CREATE TABLE IF NOT EXISTS `eleve` (
  `CodeEleve` int NOT NULL,
  `Nom` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Prenom` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Sexe` varchar(55) NOT NULL,
  `DateDeNaissance` date DEFAULT NULL,
  `Adresse1` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `CodePostal` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Ville` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Telephone` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `numCompte` int DEFAULT NULL,
  `imgPdp` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `mail` varchar(255) NOT NULL,
  PRIMARY KEY (`CodeEleve`),
  KEY `numCompte` (`numCompte`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `eleve`
--

INSERT INTO `eleve` (`CodeEleve`, `Nom`, `Prenom`, `Sexe`, `DateDeNaissance`, `Adresse1`, `CodePostal`, `Ville`, `Telephone`, `numCompte`, `imgPdp`, `mail`) VALUES
(8, 'Gnocchi', 'Gwendoline', 'Femme', NULL, '', '75008', 'Paris', '0104310779', 1, NULL, ''),
(16, 'Macaroni', 'Mazarine', 'Femme', '1982-06-01', '', '75016', 'Paris', '0118060182', 2, NULL, ''),
(18, 'Ossobucco', 'Omer', 'Homme', '1981-07-03', '', '75018', 'Paris', '0117070381', 3, NULL, ''),
(22, 'Spaghetti', 'Sophie', 'Femme', '1979-09-07', '', '75002', 'Paris', '0115090779', 4, NULL, ''),
(23, 'Tortellini', 'Théodule', 'Homme', NULL, '', '75003', 'Paris', '0114210880', 5, NULL, ''),
(41, 'Pomadoro', 'Pistache', 'Femme', NULL, '', '75001', 'Paris', '0116141178', 6, NULL, ''),
(53, 'Cannelloni', 'Célestine', 'Femme', NULL, '', '75003', 'Paris', '0171737579', 7, NULL, ''),
(57, 'Ravioli', 'Romuald', 'Homme', NULL, '', '75017', 'Paris', '0109210781', 8, NULL, ''),
(63, 'Baccalaõ', 'Brutus', 'Homme', '1979-11-09', '', '75003', 'Paris', '0145464748', 9, NULL, ''),
(64, 'Tortilla', 'Tania', 'Femme', NULL, '', '75004', 'Paris', '0122270181', 10, NULL, ''),
(70, 'Vermicellini', 'Véronique', 'Femme', NULL, '', '75010', 'Paris', '0128220381', 11, NULL, ''),
(71, 'Tagliatelli', 'Thomas', 'Homme', NULL, '', '75011', 'Paris', '0129170482', 12, NULL, ''),
(77, 'Ghappati', 'Gontrand', 'Homme', '1981-12-02', '', '75017', 'Paris', '0135120281', 13, NULL, ''),
(84, 'Busecca', 'Bruce', 'Homme', '1981-11-07', '', '75004', 'Paris', '0187868584', 14, NULL, ''),
(85, 'Carpaccio', 'Clémentine', 'Femme', NULL, '', '75005', 'Paris', '0142130781', 15, NULL, ''),
(87, 'Chipolata', 'Charlotte', 'Femme', NULL, '', '75007', 'Paris', '0144220379', 16, NULL, ''),
(91, 'Broccoli', 'Basile', 'Homme', '1981-02-07', '', '75011', 'Paris', '0176757473', 17, NULL, ''),
(92, 'Farfalle', 'Fernande', 'Femme', NULL, '', '75012', 'Paris', '0148141281', 18, NULL, ''),
(93, 'Lasagne', 'Lazare', 'Homme', NULL, '', '75013', 'Paris', '0149130481', 19, NULL, ''),
(95, 'Risotto', 'Rolande', 'Femme', NULL, '', '75015', 'Paris', '0151170280', 20, NULL, ''),
(96, 'Polenta', 'Paule', 'Femme', NULL, '', '75016', 'Paris', '0152290379', 21, NULL, ''),
(97, 'Tapioca', 'Thérèse', 'Femme', '1980-08-05', '', '75017', 'Paris', '0153080580', 22, NULL, ''),
(100, 'Mozzarella', 'Milène', 'Femme', '1979-09-10', '', '75020', 'Paris', '0156091079', 23, NULL, ''),
(102, 'Caponata', 'Clovis', 'Homme', '1980-07-06', '', '75002', 'Paris', '0158070680', 24, NULL, ''),
(106, 'Clafouti', 'Cornelia', 'Femme', NULL, '', '75006', 'Paris', '0162210281', 25, NULL, ''),
(110, 'Fettucine', 'Félicie', 'Femme', '1981-06-05', '', '75010', 'Paris', '0110060581', 26, NULL, ''),
(111, 'Rigatoni', 'Rita', 'Femme', NULL, '', '75011', 'Paris', '0161280681', 27, NULL, ''),
(112, 'Cappelletti', 'Clémence', 'Femme', '1981-12-10', '', '75012', 'Paris', '0165091011', 28, NULL, ''),
(113, 'Antipasta', 'Amadeus', 'Homme', NULL, '', '75013', 'Paris', '0123242526', 29, NULL, ''),
(114, 'Scaloppine', 'Scarlet', 'Femme', NULL, '', '75014', 'Paris', '0127091283', 30, NULL, ''),
(115, 'Anguilla', 'Amandine', 'Femme', NULL, '', '75015', 'Paris', '0112131415', 31, NULL, ''),
(116, 'Bagnacauda', 'Brigitte', 'Femme', NULL, '', '75016', 'Paris', '0156575859', 32, NULL, ''),
(117, 'Funghi', 'Fiona', 'Femme', NULL, '', '75017', 'Paris', '0187767583', 33, NULL, ''),
(118, 'Melanzane', 'Mélanie', 'Femme', NULL, '', '75018', 'Paris', '0154467985', 34, NULL, ''),
(119, 'Fagioli', 'Ferdinand', 'Homme', NULL, '', '75019', 'Paris', '0189888786', 35, NULL, ''),
(120, 'Vongole', 'Véronica', 'Femme', NULL, '', '75020', 'Paris', '0145464748', 36, NULL, ''),
(121, 'Pesce', 'Pascaline', 'Femme', NULL, '', '75001', 'Paris', '0131323334', 37, NULL, ''),
(122, 'Cozze', 'Charline', 'Femme', NULL, '', '75002', 'Paris', '0117191613', 38, NULL, ''),
(123, 'Stracciatella', 'Sabrina', 'Femme', '1975-05-05', '', '75003', 'Paris', '0131649728', 39, NULL, ''),
(124, 'Minestrone', 'Martina', 'Femme', NULL, '', '75004', 'Paris', '0195969798', 40, NULL, ''),
(125, 'Pavese', 'Pietro', 'Homme', '1979-08-07', '', '75005', 'Paris', '0107080910', 41, NULL, ''),
(126, 'Fonduta', 'Florence', 'Femme', '1979-09-09', '', '75006', 'Paris', '0103050709', 42, NULL, ''),
(127, 'Carozza', 'Cunégonde', 'Femme', NULL, '', '75007', 'Paris', '0105090307', 43, NULL, ''),
(128, 'Calzone', 'Corentin', 'Homme', NULL, '', '75008', 'Paris', '0186848280', 44, NULL, ''),
(129, 'Tortino', 'Terrence', 'Homme', '1980-05-12', '', '75009', 'Paris', '0104070205', 45, NULL, ''),
(130, 'Carciofi', 'Christian', 'Homme', NULL, '', '75010', 'Paris', '0103060908', 46, NULL, ''),
(131, 'Scampi', 'Simone', 'Femme', '1982-01-05', '', '75011', 'Paris', '0104050607', 47, NULL, ''),
(132, 'Limone', 'Léon', 'Homme', '1981-08-09', '', '75012', 'Paris', '0192969498', 48, NULL, ''),
(133, 'Bisi', 'Bénédicte', 'Femme', '1979-07-08', '', '75013', 'Paris', '0165646362', 49, NULL, ''),
(134, 'Uova', 'Ursule', 'Femme', NULL, '', '75014', 'Paris', '0103050709', 50, NULL, ''),
(135, 'Carbonara', 'Camille', 'Femme', '1979-11-10', '', '75015', 'Paris', '0151535759', 51, NULL, ''),
(136, 'Uccelletto', 'Ulla', 'Femme', NULL, '', '75016', 'Paris', '0108060402', 52, NULL, ''),
(137, 'Cavoli', 'Constant', 'Homme', NULL, '', '75017', 'Paris', '0104040404', 53, NULL, ''),
(138, 'Prosciutto', 'Priscilla', 'Femme', NULL, '', '75018', 'Paris', '0151525354', 54, NULL, ''),
(139, 'Peperonata', 'Patricia', 'Femme', '1972-08-05', '', '75019', 'Paris', '0159575553', 55, NULL, ''),
(140, 'Spinaci', 'Steve', 'Homme', '1979-06-04', '', '75020', 'Paris', '0186848280', 56, NULL, ''),
(141, 'Parmigiana', 'Patrick', 'Homme', NULL, '', '75001', 'Paris', '0102050809', 57, NULL, ''),
(142, 'Tegame', 'Thierry', 'Homme', '1976-06-16', '', '75002', 'Paris', '0104060709', 58, NULL, ''),
(143, 'Zucchini', 'Zoé', 'Femme', NULL, '', '75003', 'Paris', '0108070504', 59, NULL, ''),
(144, 'Aragosta', 'Armande', 'Femme', NULL, '', '75004', 'Paris', '0134353637', 60, NULL, ''),
(145, 'Trotelle', 'Teresa', 'Femme', NULL, '', '75005', 'Paris', '0160824281', 61, NULL, ''),
(146, 'Cacciucco', 'Christelle', 'Femme', '1980-09-11', '', '57006', 'Paris', '0197959391', 62, NULL, ''),
(147, 'Ostriche', 'Ornella', 'Femme', NULL, '', '75007', 'Paris', '0194989692', 63, NULL, ''),
(148, 'Triglie', 'Théodule', 'Homme', NULL, '', '75008', 'Paris', '0168646662', 64, NULL, ''),
(149, 'Pollo', 'Polo', 'Homme', NULL, '', '75009', 'Paris', '0174859652', 65, NULL, ''),
(150, 'Cacciatora', 'Carmen', 'Femme', '1979-09-09', '', '75010', 'Paris', '0198979695', 66, NULL, ''),
(151, 'Peperoni', 'Pierre', 'Homme', '1981-11-11', '', '75011', 'Paris', '0197643231', 67, NULL, ''),
(152, 'Vitello', 'Vincent', 'Homme', '1982-12-02', '', '75012', 'Paris', '0189452365', 68, NULL, ''),
(153, 'Cima', 'Céline', 'Femme', NULL, '', '75013', 'Paris', '0131649782', 69, NULL, ''),
(154, 'Polpette', 'Pauline', 'Femme', NULL, '', '75014', 'Paris', '0148592615', 70, NULL, ''),
(155, 'Manzo', 'Marceline', 'Femme', NULL, '', '75015', 'Paris', '0136353433', 71, NULL, ''),
(156, 'Abbacchio', 'Alberte', 'Femme', NULL, '', '75016', 'Paris', '0102030405', 72, NULL, ''),
(157, 'Vaccinara', 'Valentine', 'Femme', NULL, '', '75017', 'Paris', '0134373895', 73, NULL, ''),
(999, 'Gros', 'Petit', '2', '2006-01-10', '14 allée de la République', '92000', 'Nanterre', '0123456789', 78, NULL, ''),
(1083, 'Succes', 'Succes', 'Femme', '2024-10-28', 'Succes', 'Succes', 'Succes', 'Succes', 83, ' ', ''),
(1085, 'tryInscEleve', 'tryInscEleve', 'Femme', '2024-11-04', 'tryInscEleve', 'tryInscEleve', 'tryInscEleve', 'tryInscEleve', 85, ' ', ''),
(1086, 'noetzel', 'lorenz', 'Femme', '2003-02-28', 'jhedgfv', '92000', 'etb', '0123456789', 86, ' ', ''),
(1087, 'noetzel', 'lorenz', 'Femme', '2003-02-28', 'jhedgfv', '92000', 'etb', '0123456789', 87, ' ', ''),
(1088, 'mm', 'nn', 'Homme', '2024-11-14', 'sdfghjkhgfd', '9878', 'DFGHJKHGF', '345676543', NULL, NULL, 'test'),
(1089, 'muscat', 'virgile', 'Homme', '2024-11-06', 'dfghjgkjgfd', '93100', 'Montreuil', '9878987', 98, NULL, 'muscat'),
(1090, 'pernet', 'tom', 'Homme', '2024-11-22', 'qszdertgyhjukil', '12345', 'sdfghj', '3456785', 102, NULL, 'tom'),
(1091, 'bb', 'soso', 'Homme', '2024-11-06', 'sdfg', '93110', 'Montreuil', '9878987', 103, NULL, 'soso'),
(1092, 'huang', 'justin', 'Homme', '2024-12-26', 'dfsbgj,h', '45666', 'Aubervillier', '34565456', 104, NULL, 'justin'),
(1093, 'esteban', 'esteban', 'Homme', '2024-12-17', 'bite', '78100', 'TEUB', '9878908', 105, NULL, 'este'),
(1094, 'mariaini', 'pierre', 'Homme', '2024-11-26', 'dfghjgfg', '23333', 'DFGFD', '34567865', 106, NULL, 'pierre');

-- --------------------------------------------------------

--
-- Structure de la table `eleve_categorie`
--

DROP TABLE IF EXISTS `eleve_categorie`;
CREATE TABLE IF NOT EXISTS `eleve_categorie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `CodeEleve` int NOT NULL,
  `codeCategorie` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `CodeEleve` (`CodeEleve`),
  KEY `codeCategorie` (`codeCategorie`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `eleve_categorie`
--

INSERT INTO `eleve_categorie` (`id`, `CodeEleve`, `codeCategorie`) VALUES
(5, 8, 2),
(6, 8, 3);

-- --------------------------------------------------------

--
-- Structure de la table `lecon`
--

DROP TABLE IF EXISTS `lecon`;
CREATE TABLE IF NOT EXISTS `lecon` (
  `CodeLecon` int NOT NULL,
  `Date` date DEFAULT NULL,
  `Heure` varchar(50) DEFAULT NULL,
  `CodeMoniteur` int DEFAULT NULL,
  `CodeEleve` int DEFAULT NULL,
  `Immatriculation` varchar(50) DEFAULT NULL,
  `Reglee` int NOT NULL,
  `duree` int NOT NULL,
  PRIMARY KEY (`CodeLecon`),
  KEY `CodeMoniteur` (`CodeMoniteur`),
  KEY `CodeEleve` (`CodeEleve`),
  KEY `Immatriculation` (`Immatriculation`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `lecon`
--

INSERT INTO `lecon` (`CodeLecon`, `Date`, `Heure`, `CodeMoniteur`, `CodeEleve`, `Immatriculation`, `Reglee`, `duree`) VALUES
(8, '2016-08-23', '10:00:00', 11, 22, '123 AB 21', 1, 1),
(9, '2016-07-23', '10:00:00', 11, 63, '234 BC 21', 1, 1),
(10, '2015-12-24', '10:00:00', 11, 63, '234 BC 21', 1, 2),
(11, '2016-06-07', '10:00:00', 11, 63, '234 BC 21', 1, 2),
(16, '2016-09-05', '10:00:00', 14, 113, '789 GH 21', 1, 1),
(17, '2016-04-03', '10:00:00', 14, 113, '789 GH 21', 1, 1),
(18, '2016-03-31', '10:00:00', 14, 113, '789 GH 21', 1, 2),
(19, '2016-07-16', '10:00:00', 14, 113, '789 GH 21', 1, 2),
(20, '2016-07-12', '10:00:00', 14, 113, '789 GH 21', 1, 1),
(21, '2016-08-28', '10:00:00', 8, 113, '234 BC 21', 1, 1),
(22, '2015-12-29', '10:00:00', 8, 8, '234 BC 21', 1, 1),
(23, '2016-01-02', '10:00:00', 8, 16, '234 BC 21', 1, 1),
(24, '2016-09-22', '10:00:00', 11, 22, '456 DE 21', 1, 1),
(25, '2016-01-30', '10:00:00', 14, 23, '345 CD 21', 1, 1),
(26, '2016-04-09', '10:00:00', 8, 18, '234 BC 21', 1, 1),
(27, '2016-08-01', '10:00:00', 11, 8, '234 BC 21', 1, 2),
(28, '2016-02-14', '10:00:00', 14, 16, '234 BC 21', 1, 1),
(29, '2016-01-16', '10:00:00', 11, 23, '234 BC 21', 1, 2),
(30, '2016-01-12', '10:00:00', 8, 53, '234 BC 21', 1, 1),
(31, '2016-08-09', '10:00:00', 11, 22, '234 BC 21', 1, 1),
(32, '2016-04-02', '10:00:00', 8, 8, '234 BC 21', 1, 1),
(33, '2016-08-25', '10:00:00', 8, 8, '234 BC 21', 1, 2),
(34, '2016-02-06', '10:00:00', 8, 8, '234 BC 21', 1, 1),
(35, '2016-03-29', '10:00:00', 8, 8, '234 BC 21', 1, 2),
(36, '2015-12-25', '10:00:00', 8, 8, '234 BC 21', 1, 1),
(37, '2016-02-06', '10:00:00', 8, 8, '234 BC 21', 1, 1),
(38, '2016-08-16', '10:00:00', 11, 8, '234 BC 21', 1, 2),
(39, '2016-05-20', '10:00:00', 14, 16, '345 CD 21', 1, 1),
(40, '2016-08-08', '10:00:00', 14, 53, '456 DE 21', 1, 1),
(41, '2016-07-06', '10:00:00', 14, 18, '456 DE 21', 1, 2),
(42, '2016-02-01', '10:00:00', 11, 148, '567 EF 21', 1, 1),
(43, '2015-12-09', '10:00:00', 11, 57, '234 BC 21', 1, 1),
(44, '2016-07-13', '10:00:00', 14, 71, '456 DE 21', 1, 1),
(45, '2016-04-30', '10:00:00', 14, 71, '456 DE 21', 1, 2),
(46, '2015-12-22', '10:00:00', 14, 71, '456 DE 21', 1, 2),
(47, '2016-08-23', '10:00:00', 14, 71, '456 DE 21', 1, 1),
(48, '2016-06-05', '10:00:00', 14, 71, '456 DE 21', 1, 1),
(49, '2016-05-02', '10:00:00', 8, 116, '234 BC 21', 1, 1),
(50, '2016-08-27', '10:00:00', 8, 96, '234 BC 21', 1, 2),
(51, '2016-01-21', '10:00:00', 14, 41, '345 CD 21', 1, 1),
(52, '2016-01-09', '10:00:00', 11, 115, '345 CD 21', 1, 2),
(53, '2016-04-20', '10:00:00', 14, 128, '789 GH 21', 1, 1),
(54, '2016-01-24', '10:00:00', 14, 128, '789 GH 21', 1, 1),
(56, '2016-06-10', '10:00:00', 11, 148, '567 EF 21', 1, 1),
(57, '2016-07-11', '10:00:00', 11, 148, '567 EF 21', 1, 1),
(58, '2016-09-08', '10:00:00', 8, 85, '890 HJ 21', 1, 1),
(59, '2016-08-07', '10:00:00', 8, 145, '890 HJ 21', 1, 1),
(60, '2016-02-09', '10:00:00', 11, 8, '456 DE 21', 1, 1),
(61, '2016-06-07', '10:00:00', 11, 8, '234 BC 21', 1, 1),
(62, '2016-01-14', '10:00:00', 11, 157, '678 FG 21', 1, 2),
(63, '2016-08-10', '10:00:00', 11, 157, '678 FG 21', 1, 1),
(64, '2016-07-29', '10:00:00', 11, 157, '678 FG 21', 1, 1),
(65, '2016-06-24', '10:00:00', 11, 157, '678 FG 21', 1, 1),
(66, '2016-04-12', '10:00:00', 14, 157, '678 FG 21', 1, 1),
(67, '2016-08-10', '10:00:00', 11, 132, '123 AB 21', 1, 2),
(68, '2016-03-25', '10:00:00', 14, 8, '234 BC 21', 1, 2),
(69, '2016-07-14', '10:00:00', 15, 16, '345 CD 21', 1, 2),
(73, '2016-03-27', '10:00:00', 15, 8, '123 AB 21', 1, 1),
(74, '2016-02-19', '10:00:00', 15, 8, '123 AB 21', 1, 2),
(75, '2016-07-29', '10:00:00', 11, 8, '123 AB 21', 1, 2),
(76, '2016-02-01', '10:00:00', 14, 16, '234 BC 21', 1, 1),
(77, '2016-03-10', '10:00:00', 15, 18, '345 CD 21', 1, 2),
(79, '2016-01-19', '10:00:00', 14, 8, '234 BC 21', 1, 1),
(80, '2016-07-04', '10:00:00', 15, 16, '345 CD 21', 1, 1),
(81, '2016-04-03', '10:00:00', 11, 8, '123 AB 21', 1, 1),
(85, '2016-01-11', '10:00:00', 14, 16, '234 BC 21', 1, 1),
(86, '2016-04-29', '10:00:00', 15, 18, '345 CD 21', 1, 1),
(87, '2016-07-06', '10:00:00', 11, 8, '123 AB 21', 1, 2),
(88, '2016-06-16', '10:00:00', 14, 16, '234 BC 21', 1, 1),
(89, '2016-02-02', '10:00:00', 15, 18, '345 CD 21', 1, 1),
(90, '2016-08-23', '10:00:00', 15, 18, '789 GH 21', 0, 2),
(91, '2016-08-22', '10:00:00', 14, 18, '567 EF 21', 0, 1),
(92, '2017-09-02', '13:56:00', 11, 63, '456 DE 21', 0, 2),
(93, '2017-09-02', '13:58:00', 11, 129, '345 CD 21', 0, 1),
(94, '2017-09-02', '14:00:00', 11, 64, '345 CD 21', 0, 2),
(95, '2017-09-02', '14:09:00', 14, 64, '678 FG 21', 0, 2),
(96, '2017-09-02', '15:00:00', 11, 70, '678 FG 21', 0, 2),
(97, '2017-09-02', '14:13:00', 14, 84, '234 BC 21', 0, 2),
(98, '2017-11-17', '09:15:00', 15, 156, '890 HJ 21', 0, 1),
(99, '2017-08-23', '10:00:00', 8, 23, '567 EF 21', 0, 2),
(100, '2017-09-02', '14:19:00', 11, 16, '567 EF 21', 0, 2),
(101, '2017-09-02', '14:20:00', 14, 123, '345 CD 21', 0, 1),
(102, '2017-09-02', '14:23:00', 14, 8, '456 DE 21', 0, 1),
(103, '2017-09-02', '14:26:00', 11, 141, '456 DE 21', 0, 2),
(104, '2017-09-02', '14:28:00', 8, 16, '345 CD 21', 0, 2),
(105, '2017-09-02', '14:29:00', 11, 16, '456 DE 21', 0, 1),
(106, '2017-09-02', '14:30:00', 14, 77, '345 CD 21', 0, 1),
(107, '2017-09-02', '14:36:00', 14, 16, '234 BC 21', 0, 2),
(108, '2017-09-02', '14:40:00', 14, 16, '234 BC 21', 0, 2),
(109, '2017-09-05', '14:43:00', 11, 8, '456 DE 21', 0, 2),
(110, '2017-09-02', '08:46:00', 11, 8, '345 CD 21', 0, 2),
(111, '2017-09-05', '11:47:00', 8, 16, '345 CD 21', 0, 2),
(112, '2017-09-02', '15:03:00', 11, 8, '456 DE 21', 0, 1),
(113, '2017-09-29', '18:07:00', 11, 16, '234 BC 21', 0, 2),
(114, '2017-11-14', '02:10:00', 11, 128, '789 GH 21', 0, 1),
(115, '2017-09-02', '18:16:00', 11, 8, '345 CD 21', 0, 2),
(116, '2017-09-02', '13:17:00', 11, 8, '345 CD 21', 0, 2),
(117, '2017-11-02', '13:26:00', 11, 131, '345 CD 21', 0, 2),
(118, '2015-11-11', '02:09:00', 15, 57, '789 GH 21', 0, 2),
(119, '2020-09-09', '18:28:00', 11, 53, '456 DE 21', 0, 2),
(120, '2017-09-22', '08:00:00', 8, 23, '567 EF 21', 0, 1),
(121, '2017-09-22', '16:48:00', 11, 16, '345 CD 21', 0, 1),
(122, '2017-09-22', '02:00:00', 11, 53, '567 EF 21', 0, 1),
(123, '2017-09-27', '08:00:00', 14, 64, '678 FG 21', 0, 2),
(124, '2018-08-17', '09:02:00', 11, 102, '456 DE 21', 0, 2),
(125, '2020-06-24', '11:00:00', 14, 64, '789 GH 21', 0, 1),
(126, '2020-12-16', '14:00:00', 14, 16, '345 CD 21', 0, 2),
(127, '2020-12-16', '14:00:00', 15, 8, '678 FG 21', 0, 2),
(128, '2020-12-16', '15:00:00', 14, 126, '567 EF 21', 0, 2),
(130, '2024-12-18', '12:00:00', 11, 1090, '567 EF 21', 0, 2),
(131, '2025-01-02', '13:00:00', 15, 1090, '567 EF 21', 0, 2),
(132, '2024-12-18', '12:30:00', 11, 1090, '567 EF 21', 0, 2),
(133, '2024-12-30', '8:30:00', 11, 1090, '567 EF 21', 0, 2),
(134, '2024-12-22', '11:30:00', 8, 1090, '567 EF 21', 0, 2),
(135, '2024-12-22', '12:00:00', 8, 1090, '567 EF 21', 0, 2),
(136, '2024-12-12', '8:30:00', 11, 1090, '567 EF 21', 0, 1),
(137, '2024-12-12', '9:00:00', 11, 1090, '567 EF 21', 0, 1),
(138, '2025-01-04', '11:30:00', 11, 1090, '567 EF 21', 0, 2),
(139, '2024-12-29', '11:30:00', 11, 1090, '567 EF 21', 0, 1),
(140, '2024-12-29', '12:00:00', 11, 1090, '567 EF 21', 0, 1),
(141, '2024-12-13', '8:30:00', 11, 1090, '567 EF 21', 0, 2),
(142, '2024-12-14', '11:30:00', 11, 1090, '567 EF 21', 0, 1),
(143, '2024-12-13', '11:00:00', 11, 1090, '567 EF 21', 0, 2),
(144, '2024-12-26', '11:30:00', 11, 1090, '567 EF 21', 0, 2),
(145, '2024-12-26', '15:00:00', 11, 1090, '567 EF 21', 0, 2),
(146, '2024-12-14', '10:30:00', 11, 1090, '567 EF 21', 0, 2),
(147, '2024-12-26', '17:30:00', 11, 1090, '567 EF 21', 0, 1),
(148, '2024-12-26', '18:00:00', 14, 1090, '567 EF 21', 0, 2),
(149, '2024-12-26', '14:30:00', 14, 1090, '567 EF 21', 0, 1),
(150, '2024-12-22', '11:30:00', 15, 1090, '890 HJ 21', 0, 2),
(151, '2024-12-22', '13:00:00', 15, 1090, '890 HJ 21', 0, 1),
(152, '2024-12-22', '14:00:00', 8, 1090, '890 HJ 21', 0, 2),
(153, '2024-12-26', '17:30:00', 8, 1090, '890 HJ 21', 0, 1),
(154, '2024-12-11', '17:30:00', 11, 1090, '123 AB 21', 0, 2),
(155, '2024-12-13', '15:00:00', 14, 1090, '789 GH 21', 0, 2),
(156, '2024-12-29', '8:30:00', 8, 1093, '890 HJ 21', 0, 2),
(157, '2024-12-26', '16:00:00', 14, 1091, '678 FG 21', 0, 2),
(158, '2024-12-03', '14:30:00', 11, 1091, '123 AB 21', 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `licence`
--

DROP TABLE IF EXISTS `licence`;
CREATE TABLE IF NOT EXISTS `licence` (
  `CodeLicence` int NOT NULL,
  `CodeMoniteur` int DEFAULT NULL,
  `CodeCategorie` int DEFAULT NULL,
  `DateObtention` date DEFAULT NULL,
  PRIMARY KEY (`CodeLicence`),
  KEY `CodeMoniteur` (`CodeMoniteur`,`CodeCategorie`),
  KEY `CodeCategorie` (`CodeCategorie`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `licence`
--

INSERT INTO `licence` (`CodeLicence`, `CodeMoniteur`, `CodeCategorie`, `DateObtention`) VALUES
(1, 11, 1, NULL),
(2, 11, 4, NULL),
(3, 11, 5, NULL),
(4, 8, 3, NULL),
(5, 14, 1, NULL),
(6, 14, 2, NULL),
(7, 14, 5, NULL),
(8, 15, 1, NULL),
(9, 15, 3, NULL),
(10, 11, 2, NULL),
(11, 11, 3, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `moniteur`
--

DROP TABLE IF EXISTS `moniteur`;
CREATE TABLE IF NOT EXISTS `moniteur` (
  `CodeMoniteur` int NOT NULL,
  `Nom` varchar(50) DEFAULT NULL,
  `Prenom` varchar(50) DEFAULT NULL,
  `Sexe` varchar(55) DEFAULT NULL,
  `DateDeNaissance` date DEFAULT NULL,
  `Adresse1` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `CodePostal` varchar(50) DEFAULT NULL,
  `Ville` varchar(50) DEFAULT NULL,
  `Telephone` varchar(50) DEFAULT NULL,
  `numCompte` int DEFAULT NULL,
  `imgPdp` varchar(255) NOT NULL,
  PRIMARY KEY (`CodeMoniteur`),
  KEY `numCompte` (`numCompte`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `moniteur`
--

INSERT INTO `moniteur` (`CodeMoniteur`, `Nom`, `Prenom`, `Sexe`, `DateDeNaissance`, `Adresse1`, `CodePostal`, `Ville`, `Telephone`, `numCompte`, `imgPdp`) VALUES
(8, 'Béal', 'Géraldine', 'Femme', '1972-01-23', '12, avenue du Collège', '75004', 'Paris', '0180123456', 74, '0'),
(11, 'Ambrosi', 'PierrOT', 'Homme', '1969-01-04', '23, rue du Lycée', '75019', 'Paris', '180234567', 75, '0'),
(14, 'Catard', 'Olivier', 'Homme', '1963-09-12', '34, boulevard de l\'Université', '75005', 'Paris', '0180345677', 76, '0'),
(15, 'Ezard', 'Régine', 'Femme', '1978-04-06', '45, rue des Écoles', '75020', 'Paris', '0180456789', 77, '0'),
(79, 'Zie', 'Lena', 'Femme', '2006-01-10', '6 allée de la République', '92000', 'Nanterre', '0777266575', 79, 'Null'),
(1081, 'Succes', 'Succes', 'Femme', '2004-12-29', 'Succes', 'Succes', 'Succes', 'Succes', 81, ' '),
(1084, 'tryInscMoniteur', 'tryInscMoniteur', '0', '2024-11-13', 'tryInscMoniteur', 'tryInscMoniteur', 'tryInscMoniteur', 'tryInscMoniteur', 84, ' ');

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

DROP TABLE IF EXISTS `vehicule`;
CREATE TABLE IF NOT EXISTS `vehicule` (
  `Immatriculation` varchar(50) NOT NULL,
  `Marque` varchar(50) DEFAULT NULL,
  `Modele` varchar(50) DEFAULT NULL,
  `Annee` int DEFAULT NULL,
  `CodeCategorie` int DEFAULT NULL,
  PRIMARY KEY (`Immatriculation`),
  KEY `CodeCategorie` (`CodeCategorie`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `vehicule`
--

INSERT INTO `vehicule` (`Immatriculation`, `Marque`, `Modele`, `Annee`, `CodeCategorie`) VALUES
('123 AB 21', 'Mercedes', 'Spania', 2000, 1),
('234 BC 21', 'Peugeot', 'Sisancys', 1996, 1),
('345 CD 21', 'Renault', 'Morgane', 1995, 1),
('456 DE 21', 'Peugeot', 'Catsansys', 1999, 1),
('567 EF 21', 'Kawasaki', 'Zephyr', 1997, 4),
('678 FG 21', 'Renault', 'Betton', 1999, 5),
('789 GH 21', 'Iveco', 'Roader', 1998, 2),
('890 HJ 21', 'Oceansea', 'Tempest', 1999, 3);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `eleve`
--
ALTER TABLE `eleve`
  ADD CONSTRAINT `eleve_ibfk_1` FOREIGN KEY (`numCompte`) REFERENCES `compte` (`numCompte`);

--
-- Contraintes pour la table `eleve_categorie`
--
ALTER TABLE `eleve_categorie`
  ADD CONSTRAINT `eleve_categorie_ibfk_2` FOREIGN KEY (`codeCategorie`) REFERENCES `categorie` (`CodeCategorie`);

--
-- Contraintes pour la table `lecon`
--
ALTER TABLE `lecon`
  ADD CONSTRAINT `lecon_ibfk_1` FOREIGN KEY (`CodeMoniteur`) REFERENCES `moniteur` (`CodeMoniteur`),
  ADD CONSTRAINT `lecon_ibfk_3` FOREIGN KEY (`Immatriculation`) REFERENCES `vehicule` (`Immatriculation`);

--
-- Contraintes pour la table `licence`
--
ALTER TABLE `licence`
  ADD CONSTRAINT `licence_ibfk_1` FOREIGN KEY (`CodeMoniteur`) REFERENCES `moniteur` (`CodeMoniteur`),
  ADD CONSTRAINT `licence_ibfk_2` FOREIGN KEY (`CodeCategorie`) REFERENCES `categorie` (`CodeCategorie`);

--
-- Contraintes pour la table `moniteur`
--
ALTER TABLE `moniteur`
  ADD CONSTRAINT `moniteur_ibfk_1` FOREIGN KEY (`numCompte`) REFERENCES `compte` (`numCompte`);

--
-- Contraintes pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD CONSTRAINT `vehicule_ibfk_1` FOREIGN KEY (`CodeCategorie`) REFERENCES `categorie` (`CodeCategorie`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
