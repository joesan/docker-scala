package com.q31

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
package object dockerscala {

  type ContainerId = String
  type TimeOut = Int

  sealed trait PauseUnPauseContainer
  case object  PauseContainer extends PauseUnPauseContainer
  case object  UnPauseContainer extends PauseUnPauseContainer

  sealed trait StopRestartContainer
  case object  StopContainer extends StopRestartContainer
  case object  RestartContainer extends StopRestartContainer

}
