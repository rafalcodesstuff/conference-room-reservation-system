export interface ReservationResponse {
    id: number,
    created: string,
    modified: string,
    name: string,
    maxAttendees: number,
    startDateTime: string,
    endDateTime: string,
    color: string,
    eventOrganizer: string,
    attendees: [],
    conferenceRoom: string,
    allDay: false
}