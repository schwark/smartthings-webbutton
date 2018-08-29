/**
 *  Web Button for SmartThings
 *  Schwark Satyavolu
 *  Originally based on: Allan Klein's (@allanak) and Mike Maxwell's code
 *
 *  Usage:
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */

metadata {
	definition (name: "Web Button", namespace: "schwark", author: "Schwark Satyavolu") {
	capability "Switch"
	capability "Polling"
	capability "Button"
	capability "Refresh"
	input("url", "string", title:"Url", description: "Paste URL (from cURL dump)", required: true, displayDuringSetup: true, defaultValue: true )
	input("headers", "string", title:"Headers", description: "Paste headers (from cURL dump)", required: false, displayDuringSetup: true, defaultValue: false )
	input("data", "string", title:"Data", description: "Paste data (from cURL dump)", required: false, displayDuringSetup: true, defaultValue: false )
	}

simulator {
		// TODO: define status and reply messages here
	}

tiles {
	standardTile("switch", "device.switch", width: 3, height: 2, canChangeIcon: true) {
        	state "on", label: '${name}', action: "switch.off", icon: "st.tesla.tesla-front", backgroundColor: "#79b821"
        	state "off", label: '${name}', action: "switch.on", icon: "st.tesla.tesla-front", backgroundColor: "#ffffff"
   		}
   	standardTile("refresh", "device.refresh", inactiveLabel: false, decoration: "flat", width: 1, height: 1) {
			state "default", label:"", action:"refresh.refresh", icon:"st.secondary.refresh"
		}
	}

preferences {
}

    main "switch"
    details(["switch","refresh"])
}

def updated() {
}

def runCommand() {
	parent.runCommand(settings.url, settings.headers, settings.data)
}

def poll() {
	refresh()
}

def push() {
    runCommand()
}

def refresh() {
	log.debug("running device refresh for web button switch")
}

def on() {
	runCommand()
	sendEvent(name: "switch", value: "on")
}

def off() {
	runCommand()
	sendEvent(name: "switch", value: "off")
}



