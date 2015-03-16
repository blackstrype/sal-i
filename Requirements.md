# Requirements #

The following are the proposed requirements collected from the client. The requirements have been split into functional and nonfunctional requirements as well as subcategories.

## Functional requirements ##

### General ###

These requirements characterize the web interface in general. The client mentioned only some basic requirements regarding the interface and left most of the design of the interface up to the projects team.

  1. The interface should be web-based. (Accessible over HTTP).
  1. It should be dynamic. That means in particular:
    1. New SAL events trigger notifications on the interface. In particular when:
      * New devices are connected,
      * Devices are disconnected.
    1. It should reload the data every few (3-5) seconds.
  1. It is supposed to be modern / visually appealing to suit the expectations of the end users (using state of the art technology).
    1. Reload only the data, not the whole page.
    1. Interaction with the user should be smooth and intuitive.

### Regarding SAL ###
These requirements describe which parts of the SAL Client functionality should be mirrored by SAL-I.

  1. Supported sensor types should be:
    1. i-Wire (temperature sensors, pressure sensors, etc.)
    1. JPEG / V4L (web cams, etc.)
    1. SMNP (collection of sensors)
  1. The interface shall be capable of displaying a list of all sensors connected to SAL and provide the following operations on the list:
    1. Add a sensor
    1. Remove a sensor
    1. Add a protocol
    1. Remove a protocol
  1. The interface shall be capable of piloting the different sensors in the following manner:
    1. Send commands to the sensors
    1. Change individual sensor settings
  1. The interface should also provide the capability to display data provided by individual sensors in an appropriate way. Thereby it should support different types of data, especially:
    1. Textual data from i-Wire sensors (temperature sensors, pressure sensors, etc.)
    1. Streaming data from JPEG / V4L (web cams, etc.)
  1. The interface shall provide the capability to display information about the operating system the SAL Agent is running on

### Technical ###
The following requirements refer to technical requirements requested by the client.

  1. Connect to SAL agent remotely by using the SAL Client API.
  1. Wrap the SAL client API into a web service interface (In the future the SAL agent will provide Web Service interfaces which will make the SAL client API obsolete)

## Non-Functional requirements ##
  * SAL-I shall be published under an open source license.
  * The performance of the architecture needs to capable of effectively presenting video, audio, and other complex data types.
  * The system needs to be extensible for supporting the presentation of future complex data types.

## Top 10 requirements ##
The following summarizes the top level requirements in order of priority:

  1. Interface shall be Web-based
  1. It shall connect remotely to the SAL Agent
  1. It shall be able to list all the sensors
  1. It shall be able to pilot the sensors
  1. It shall be able to display the data of the sensors
  1. It shall be dynamic / SAL events trigger notifications
  1. It shall use Web-service interfaces
  1. It shall be modern / visual appealing
  1. It shall be published under an open source license
  1. It shall be extensible / support new sensors