package ar.edu.ub.buscaminas.testing.listenerprueba;

import java.util.Collection;

import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.listener.JuegoListener;


	public class JuegoListenerPrueba implements JuegoListener{
		private boolean hayGanador;
		private boolean hayPerdedor;
		private boolean hayEmpate;
		private boolean hayCambioTurno;
		
		public JuegoListenerPrueba() {
			this.setHayGanador(false);
			this.setHayPerdedor(false);
			this.setHayEmpate(false);
			this.setHayCambioTurno(false);
		}

		@Override
		public void mostrarGanador(Jugador jugador) {
			this.setHayGanador(true);			
		}

		@Override
		public void mostrarPerdedor(Jugador jugador) {
			this.setHayPerdedor(true);			
		}

		@Override
		public void mostrarEmpate(Collection<Jugador> jugadores) {
			this.setHayEmpate( true );
			
		}

		@Override
		public void pedirCambioDeTurno() {
			this.setHayCambioTurno(true);			
		}

		public boolean isHayEmpate() {
			return hayEmpate;
		}

		private void setHayEmpate(boolean hayEmpate) {
			this.hayEmpate = hayEmpate;
		}

		public boolean isHayPerdedor() {
			return hayPerdedor;
		}

		private void setHayPerdedor(boolean hayPerdedor) {
			this.hayPerdedor = hayPerdedor;
		}

		public boolean isHayGanador() {
			return hayGanador;
		}

		private void setHayGanador(boolean hayGanador) {
			this.hayGanador = hayGanador;
		}

		public boolean isHayCambioTurno() {
			return hayCambioTurno;
		}

		public void setHayCambioTurno(boolean hayCambioTurno) {
			this.hayCambioTurno = hayCambioTurno;
		}
		
	}

