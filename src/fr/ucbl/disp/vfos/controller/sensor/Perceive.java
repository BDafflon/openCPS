

package fr.ucbl.disp.vfos.controller.sensor;

import fr.ucbl.disp.vfos.controller.data.AData;

public interface Perceive {

   public AData perceive()throws InterruptedException;

}