package com.grupo6.ConectaJob.MentoriasMarketPlace.Model.DTO.Mentor;

import com.grupo6.ConectaJob.MentoriasMarketPlace.Model.Mentor.Mentor;
import com.grupo6.ConectaJob.Model.DTO.searchDTO;

public record EditMentorDTO(searchDTO searchId, Mentor novoMentor) {
}
