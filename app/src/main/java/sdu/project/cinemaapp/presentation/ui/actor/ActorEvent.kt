package sdu.project.cinemaapp.presentation.ui.actor

interface ActorEvent {
    data object OnBackClick : ActorEvent
    data class LoadActor(val actorId: Int) : ActorEvent
}