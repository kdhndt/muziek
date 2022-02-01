insert into albums(artiestId, labelId, naam, jaar, barcode) values
((select id from artiesten where naam = 'test'), (select id from labels where naam = 'test'), 'testAlbum', 1999, 5678);