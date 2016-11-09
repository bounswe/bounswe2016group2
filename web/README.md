# Running project on local server

Sorry for latency. The project is organized and tested with MacOS Sierra 10.12, Ubuntu 16.04.1 LTS. I am going to go over step by step to explain how to run the project on local server for MacOS and Ubuntu. For Windows users, well, just don't use Windows.

### Install Python3.X (Python3 or above)

MacOS: [Download](https://www.python.org/downloads/)

Ubuntu:
* `apt-get install python3`
* `apt-get install python3-pip` (Python Package Index is installed automatically on OS X)


***

### Install [Django](https://www.djangoproject.com/) 1.10.X

MacOS / Ubuntu:
* `pip3 install Django` (Easiest way)


***

### Install MySQL

MacOS: [Download](http://dev.mysql.com/doc/refman/5.7/en/osx-installation-pkg.html)
  > _You can also install via Homebrew or any way you desire, but make sure mysql command is available in `$PATH` environment variable._

Ubuntu:
* `apt-get install mysql-server`
* `apt-get install libmysqlclient-dev`
* `mysql_install_db`

> _For now, your MySQL username and password must be the same as defined in SETINGS.py, but it is temporary and going to be configurable. Application is going to be able to get credentials from environment variables up to the next milestone. You can change credentials in SETTINGS.py for now as a workaround, but remember not to commit it._

***

### Create database

> _You can connect to your local MySQL server via command line or any desktop application you want. For Mac users, I recommend [Sequel Pro](https://www.sequelpro.com/), which is a light-weight application capable of doing everything we will need. There is also a bunch of tools for Ubuntu as well, like [MySQL Workbench](http://dev.mysql.com/downloads/workbench/), [phpMyAdmin](https://www.phpmyadmin.net/), etc._

MacOS / Ubuntu:
* `mysql -u root -p`
* `Enter password:`
* `mysql> CREATE DATABASE eatright;`

***

### Install [NodeJS](https://nodejs.org/en/)

> _Since we decided to use [ReactJS](https://facebook.github.io/react/), We will need to use [JSX](https://jsx.github.io/) language together with [ES6](https://jsx.github.io/) syntax. Since browsers do not interpret JSX code, we are going to transpile React code into standard JavaScript code and put it in appropriate folders to be served by Django. There is no suitable module in Python for this purpose. So we have to install [Babel](https://babeljs.io/), which is an awesome transpiler designed specifically for this job and is available on [npm](https://www.npmjs.com/). NodeJS will be used for this purpose and will be necessary only in development environment._

> _Since we already need to use NodeJS, Let's not put our 3rd party libraries into the repository. We will install a bunch of frontend libraries which means much more awesome functionality but also wasted data, so we can define them in the repo, then install them later on with [Bower](https://bower.io/) which is the most popular package manager for the web._

MacOS: [Download](https://nodejs.org/en/download/)

Ubuntu:
* `curl -sL https://deb.nodesource.com/setup_6.x | sudo -E bash -`
* `sudo apt-get install -y nodejs`

MacOS / Ubuntu:
* `sudo npm install -g  bower` (Install Bower as global package)
* `sudo npm install -g babel-cli` (Install Babel as global package)

> _For the ones who are going to code ReactJS, I strongly advice [Atom](https://atom.io/), a hackable text editor for 21th century. I even use it for Django. It is not only much faster than PyCharm and WebStorm, but also speeds up development process for React dramatically, handling all transpilation process under the hood. If you decide to move to Atom, mandatory packages for our project are the following: [linter-flake8](https://atom.io/packages/linter-flake8), [atom-django](https://atom.io/packages/atom-django) and the most important one [language-babel](https://atom.io/packages/language-babel). If you decide to stick with PyCharm, you will need to use `babel` command with necessary parameters to trinspile JSX into JS_.

***

### Start the project
* `git clone https://github.com/bounswe/bounswe2016group2.git`
* `cd bounswe2016group2/web`
* `npm install` (Install babel presets)
* `bower install` (Install frontend libraries)
* `pip3 install -r /path/to/project/web/requirements.txt` (Install necessary python modules)
* `python3 manage.py migrate` (Create or update the database structure)
* `python3 manage.py runserver` (Start server)

***

Go to http://127.0.0.1:8000 and see if it works

Use API on http://127.0.0.1:8000/api base. [Postman](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop) is recommended


For detailed information about API, check the documentation (@TODO: link)
