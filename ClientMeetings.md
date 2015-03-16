# 2008-09-01 #

## General ##

**Where:** Office of Gilles Gigan

**When:** 01/09/2008 01:30pm (week 5)

**Who:** Chris Christensen, Joerg Seifert, Marc Hammerton, **Scott Messner**

## Topics ##

  * We went to talk to Gilles to better define this feature set and discuss potential risks with different system architectures.
    * Display of User Friendly list of components/devices
      * Find a way to display all devices -- active and inactive
    * Display of OS configuration details pertinent to SAL.
    * Display of individual device information
      * 1-wire sensors
        * Tempurature
        * Motion
        * Humidity
        * Pressure
        * Seismic
        * Infrared
      * SNMP sensors
        * Switch for connecting a subnetwork of sensors to SAL
      * Video/Camera/Audio feed
        * Provide the ability to display streaming video/audio data to the user.
    * Provide Graphical User Interface for the following:
      * Piloting of Sensors -- Ability to change a device's configurations through SAL
      * Manually add/remove sensors (Useful when SAL doesn't automatically recognize new devices)
        * Details will be provided by Gilles later
    * We also discussed with Gilles, documentation of SAL and where to find it.  For the most part, it's in the commenting of the code
      * .../SAL/svn/common folder will have the most relevant information for us as client-side developers
      * .../SAL/svn/common/agents/RMiSALclient.java contains the code for the SAL client
        * According to Gilles, we will be able to use most of this code to develop our own SAL client interface web service.
          * It will be the communication between the web-browser and the SAL agent (server)
          * It will use the SAL client object
      * .../SAL/svn/client/RMiclient.java contains more referencable code for developing our web service component
  * As part of the project proposal, we need to identify the top 10 risks to the development of our system
    * Examples:
      * We all have limited experience in web development
      * SAL is meant to provide interface to any possible sensor - This means making SAL-I capable of easily supporting new tech devices.

## Next Planned Meeting ##
Check date and time per email with Gilles



# 2008-08-26 #

## General ##

**Where:** Office of Gilles Gigan

**When:** 26/08/2008 (week 4)

**Who:** Chris Christensen, Joerg Seifert, Marc Hammerton, **Scott Messner**, Xu Du; SAL-T: Jochen Braun, Andreas Knirsch, Andreas Seemann

## Topics ##

  * How to access the source code for SAL and other needed softwares
    * SAL and it's working parts are stored in a Subversion repository
      * http://www.hpc.jcu.edu.au/projects/SAL/svn
        * ./SAL/branches/RMi contains the Remote Method Invocation tool used to create the client and servers environments for the SAL system
        * ./v4l4j/trunk contains the Video 4 Java 4 Linux package.  I found a wiki that describes it: http://www.hpc.jcu.edu.au/projects/SAL/wiki/V4L4J
        * ./HAL/trunk is also necessary.  From what I remember, it contains software packages for interpreting connected hardware to the SAL server hosting computer (correct me if I'm wrong)
      * Note: he told us not to check out the trunk - we won't need all the data stored within the repos.
  * How to compile and run SAL
    * Gilles quickly showed us how to compile, setup, and run the client and server system for SAL.  This is a step-by-step process it seems, so hopefully I haven't missed anything.  If I am, hopefully it won't be too difficult to figure out what's going on.
    * Use ant (make for java) which is part of JDK 1.6 (or higher if we want to take that risk)
      * type 'ant clean compile' to completely rebuild the SAL and HAL directories
      * for the v4l4j directory, type 'and clean jinilib compile'
    * Set up a RMI registry
      * type 'rmi registry'
    * At this point we should be able to run the server and client for SAL.
      * './run-rmi-server.sh (computerIP) (serverIP)' will run the server
      * './run-rmi-client.sh (clientIdentifier) (compIP) (serverIP)' will run the client
    * We will need a linux machine with JDK 1.6 and probably a fair amount of patience. Gilles verified that we can run the client and server on one machine.
  * Gilles demoed the following capabilities from the client perspective:
    * SAL provides a base menu for doing a number of things.  I noted the following:
      * List all components/devices as xml
      * List all components/devices (human readable)
      * Add new component/device
      * Select component to focus on
    * SAL provides a submenu for a focused component/device
      * List commands available for component (xml)
      * List commands available for component (human readable)
      * Send command to component

## Next Planned Meeting ##
Check date and time per email with Gilles