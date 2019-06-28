#!/bin/bash

############################################################################################################################
#                                                                                                                          #
# SCRIPT GOES TO A DEPLOYMENT DIRECTORY AND REPLACES ALL RESOURCES AND VIEWS                                               #
# WITH HARD LINKS ON RESPECTIVE RESOURCES AND VIEWS IN DEVELOPMENT DIRECTORY.                                              #
#                                                                                                                          #
# PROS: I can change styles and pages without redeploing.                                                                  #
# CONS: 1) Due to the fact that  I do not want mess file permissions in development directory, tomcat can only read files. #
#          Tomcat can't write to those files, because he hasn't permissions.                                               #
#       2) Before redeploing need run clean.sh                                                                             #
#       3) clean.sh and link.sh run as sodo                                                                                #
#                                                                                                                          #
# MAYBE LATER I'LL FIND MORE SOPHISTICATED WAY.                                                                            #
#                                                                                                                          #
############################################################################################################################


trap 'for file in $(find /var/lib/tomcat8/webapps/eatfood/WEB-INF); do if [ -d "$file" ]; then chown tomcat8 "$file"; fi; done' EXIT


for file in $(find /var/lib/tomcat8/webapps/eatfood/resources); do
    if [ -d "$file" ]; then
	chown belenot "$file"
    fi
    if [ -f "$file" ]; then
	link=$file
	rm $file
	file=$(echo "$file" | sed -e 's/\/var\/lib\/tomcat8\/webapps\/eatfood/\/home\/belenot\/programming\/projects\/java\/belenot\/eatfood\/src\/main\/webapp/g')
	ln $file $link
    fi
done

for file in $(find /var/lib/tomcat8/webapps/eatfood/WEB-INF/view); do
    if [ -d "$file" ]; then
	chown belenot "$file"
    fi
    if [ -f "$file" ]; then
	link=$file
	rm $file
	file=$(echo "$file" | sed -e 's/\/var\/lib\/tomcat8\/webapps\/eatfood/\/home\/belenot\/programming\/projects\/java\/belenot\/eatfood\/src\/main\/webapp/g')
	ln $file $link
    fi
done
