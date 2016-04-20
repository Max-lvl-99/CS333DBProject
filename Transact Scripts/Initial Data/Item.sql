Use MushVsGrump;
GO
--If we need to really reset the table delete rows and reset identity seed
--before inserting again.
--Delete From Item;
--DBCC CHECKIDENT ('Item', RESEED, 0);
--First insert must be user character so it is index 1
INSERT INTO Item (ItName, ItDesc)
	VALUES ('Weak Healing Potion', 
	'This potion heals your health by 15% of your maximum possible health.');
INSERT INTO Item (ItName, ItDesc)
	VALUES ('Moderate Healing Potion',
	'This potion heals your health by 25% of your maximum possible health.');
INSERT INTO Item (ItName, ItDesc)
	VALUES ('Strong Healing Potion', 
	'This potion heals your health by 50% of your maximum possible health.');
INSERT INTO Item (ItName, ItDesc)
	VALUES ('Weak Sword Poison',
	'This poison increases the damage dealt by any sword by 10% when applied ' +
	'to the sword.  Once applied to a sword, it cannot be reused.');
INSERT INTO Item (ItName, ItDesc)
	VALUES ('Alchemist''s Sword Poison',
	'This is an alchemist''s special poison to be used on any sword.  ' + 
	'This poison increases the damage dealt by any sword by 15%.' +
	'Once applied to a sword, it cannot be reused.');