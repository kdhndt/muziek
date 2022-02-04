insert into albums(artiestId, labelId, naam, jaar, barcode) values
((select id from artiesten where naam = 'testArtiest'), (select id from labels where naam = 'testLabel'), 'testAlbum', 1999, 5678);

insert into tracks(albumId, naam, tijd) VALUES
((select id from albums where naam = 'testAlbum'), 'testTrack', '23:50:26');