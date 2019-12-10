# BookCatalog (SECURITY VULNERABILITY NOTED)
Book Catalog is an ebook manager.

Book Catalog creates a two directories:
D:\edu_repo\ebooks_test\
D:\edu_repo\ebooks_test\DELETE

Upon application start, click Initialize in the navigation links at the top.
Apache Tika is used to start looking from all ebooks in the \ebook directory. It attempts to find the books title, publisher, publish date and other meta data. Unfortunately, it does not work very well but I have still configured it to return:
Author, Publish Date, File Name and Book Title

If you have any files at all in your ebooks_test directory, they will show up in the table on the page with metadata as available by Tika parser.




Planned features:

Simple and Detail view for all ebooks
View ebooks themselves
Login (basic Spring security)
Edit book meta data
Mark books for deletion so that they are moved to a filesystem folder where deletions can be reviewed before they are really done!
