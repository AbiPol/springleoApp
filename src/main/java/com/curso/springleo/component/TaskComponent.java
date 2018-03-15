package com.curso.springleo.component;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("taskComponent")
public class TaskComponent {

	 private static final Log LOG = LogFactory.getLog(TaskComponent.class);
	 
	 /* Indicamos a spring que es una repeticion y con fixedDelay le decimos el tiempo en milisegundos
	  * HAy que ir al main (SpringLeoAplicattion.java) y poner la etiqueta @EnableScheduling
	  * Estas tareas se usan para envio de correos, borrado de correos.
	  */
	 @Scheduled(fixedDelay = 5000)
	 public void doTask() {
		 LOG.info("Time is: " + new Date());
	 }
			 
}
